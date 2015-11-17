package ro.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.domain.FormError;
import ro.ducati.entity.MemoItem;
import ro.ducati.service.CoreService;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class MemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoController.class);

    @Autowired
    private CoreService coreService;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextArea memoContent;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private DatePicker lastModifiedDate;
    @FXML
    private DatePicker addedDate;
    @FXML
    private TextField shortDescriptionTextBox;
    @FXML
    private AnchorPane actionViewContainer;
    @FXML
    private GridPane addMemoView;

    private GridPane addCategoryView;

    private ListView<String> memoItemListView;

    private TextField searchTextBox;

    private List<FormError> validationErrors;

    private ObservableList<MemoItem> observableMomoItemList;

    private MemoItem alreadyExistItem;

    @FXML
    void saveItem(ActionEvent event) {
        final MemoItem memoItem = getMemoItem();
        if (isValidForm()) {
            observableMomoItemList.add(memoItem);
            coreService.save(memoItem);
            clearForm(new ActionEvent());
            loadMemoItems();
            LOGGER.debug("Application save event [{}]", memoItem);
        } else {
            if (alreadyExistItem != null &&
                    alreadyExistItem.getContent().hashCode() != memoItem.getContent().hashCode()) {
                memoItem.setId(alreadyExistItem.getId());
                coreService.save(memoItem);
                loadMemoItems();
                LOGGER.debug("Memo Item updated [{}]", memoItem);
                alreadyExistItem = null;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                String errorMessages = validationErrors.stream()
                        .map(FormError::toString).reduce("", (a, b) -> a.concat("\n" + b));
                alert.setContentText(errorMessages);
                alert.setHeaderText(null);
                alert.show();
            }
        }
    }

    private MemoItem getMemoItem() {
        return new MemoItem(
                categoryComboBox.getSelectionModel().getSelectedItem(),
                shortDescriptionTextBox.getText(),
                addedDate.getValue(),
                lastModifiedDate.getValue(),
                memoContent.getText());
    }

    private boolean isValidForm() {
        validationErrors = new ArrayList<>();
        addMemoView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                if (StringUtils.isEmpty(((TextInputControl) node).getText())) {
                    validationErrors.add(new FormError(node, FormError.ERROR_TYPE.EMPTY_FIELD));
                }
            }
            if (node instanceof ComboBox) {
                if (StringUtils.isEmpty(((ComboBox) node).getSelectionModel().getSelectedItem())) {
                    validationErrors.add(new FormError(node, FormError.ERROR_TYPE.EMPTY_FIELD));
                }
            }
        });
        for (MemoItem memoItem : coreService.find(shortDescriptionTextBox.getText().trim())) {
            if (memoItem.getCategory().equals(categoryComboBox.getSelectionModel().getSelectedItem()))
                alreadyExistItem = memoItem;
            validationErrors.add(new FormError(shortDescriptionTextBox, FormError.ERROR_TYPE.ALREADY_EXIST));
        }
        return validationErrors.size() == 0;
    }

    @FXML
    void clearForm(ActionEvent event) {
        addMemoView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                ((TextInputControl) node).clear();
            }
        });
    }

    @FXML
    void deleteItem(ActionEvent event) {
        String selectedItem = (String) memoItemListView.getSelectionModel().getSelectedItem();
        LOGGER.debug("deleting item [{}]", selectedItem);
        memoItemListView.getItems().remove(selectedItem);
        observableMomoItemList.stream().filter(item -> item.getShortDescription().equals(selectedItem))
                .findFirst().ifPresent(item -> {
            coreService.delete(item);
            observableMomoItemList.remove(item);
        });
    }

    @FXML
    void initialize() {
        LOGGER.debug("Initializing [{}]", MemoController.class.getSimpleName());

        Platform.runLater(() -> {
            LOGGER.debug("RunLater method triggered.");
            ViewContainerController viewContainerController = (ViewContainerController) VCStore.getController(ViewContainerController.class);
            this.memoItemListView = viewContainerController.getTvMemoItems();
            this.searchTextBox = viewContainerController.getSearchTextBox();
            this.observableMomoItemList = FXCollections.observableArrayList();
            memoItemListView.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        displaySelectedMemoItem(newValue);
                    });
            searchTextBox.textProperty().addListener((observable, oldValue, newValue) -> filterSearch(newValue));
            refresh();
        });

    }

    private void filterSearch(String searchText) {
        if (!StringUtils.isEmpty(searchText)) {
            LOGGER.debug("filtering memoitems has description like [{}]",searchText);
            List<String> searchMatches = observableMomoItemList.stream()
                    .filter(item -> item.getShortDescription().toLowerCase().contains(searchText.toLowerCase()))
                    .map(MemoItem::getShortDescription)
                    .collect(Collectors.toList());
            memoItemListView.getItems().clear();
            memoItemListView.setItems(FXCollections.observableArrayList(searchMatches));
        }else{
            List<String> searchMatches = observableMomoItemList.stream()
                    .map(MemoItem::getShortDescription)
                    .collect(Collectors.toList());
            memoItemListView.getItems().clear();
            memoItemListView.setItems(FXCollections.observableArrayList(searchMatches));
        }
    }

    private void displaySelectedMemoItem(Object selectedValue) {
        if (selectedValue instanceof String) {
            observableMomoItemList.stream().filter(item -> item.getShortDescription().equals(selectedValue))
                    .findFirst().ifPresent(item -> {
                categoryComboBox.getSelectionModel().select(item.getCategory());
                addedDate.setValue(item.getDateAdded());
                lastModifiedDate.setValue(item.getDateModified());
                shortDescriptionTextBox.setText(item.getShortDescription());
                memoContent.setText(item.getContent());
            });
        }
    }


    @FXML
    void refresh() {
        LOGGER.debug("refreshing the addMemoView");
        addedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        lastModifiedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        loadCategories();
        loadMemoItems();
    }

    private void loadMemoItems() {
        LOGGER.debug("loading memo items");
        observableMomoItemList.clear();
        coreService.findAllMemoItems().forEach(observableMomoItemList::add);
        if (memoItemListView == null) {
            LOGGER.debug("loading memoItemListView");
            this.memoItemListView = ((ViewContainerController) VCStore.getController(ViewContainerController.class))
                    .getTvMemoItems();
        }
        if (memoItemListView != null) {
            LOGGER.debug("memoItemListView Loaded");
            memoItemListView.getItems().clear();
            observableMomoItemList.forEach(memoItem -> memoItemListView.getItems()
                    .add(memoItem.getShortDescription()));
        } else {
            LOGGER.error("memoItemListView loading failed");
        }
    }

    private void loadCategories() {
        LOGGER.debug("loading categories");
        categoryComboBox.getItems().clear();
        coreService.findAllCategories().forEach(category -> {
            categoryComboBox.getItems().add(category.getLabel());
        });
    }
}
