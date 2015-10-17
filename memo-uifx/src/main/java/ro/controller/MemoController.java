package ro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ComboBox<String> memoCategory;
    @FXML
    private DatePicker memoLastModifiedDate;
    @FXML
    private DatePicker memoAddedDate;
    @FXML
    private TextField memoShortDescription;
    @FXML
    private AnchorPane actionViewContainer;
    @FXML
    private GridPane addMemoView;

    private GridPane addCategoryView;

    private ListView lvMemoItems;

    private List<FormError> validationErrors;

    private ObservableList<MemoItem> memoItemList;

    @FXML
    void saveItem(ActionEvent event) {
        if (isValidForm()) {
            final MemoItem memoItem = new MemoItem(
                    memoCategory.getSelectionModel().getSelectedItem(),
                    memoShortDescription.getText(),
                    memoAddedDate.getValue(),
                    memoLastModifiedDate.getValue(),
                    memoContent.getText());
            memoItemList.add(memoItem);
            coreService.save(memoItem);
            clearForm(new ActionEvent());
            loadMemoItems();
            LOGGER.debug("Application save event [{}]", memoItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String errorMessages = validationErrors.stream()
                    .map(FormError::toString).reduce("", (a, b) -> a.concat("\n" + b));
            alert.setContentText(errorMessages);
            alert.setHeaderText(null);
            alert.show();
        }
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
        for (MemoItem memoItem : coreService.find(memoShortDescription.getText().trim())) {
            if (memoItem.getCategory()
                    .equals(memoCategory.getSelectionModel().getSelectedItem()))
                validationErrors
                        .add(new FormError(memoShortDescription, FormError.ERROR_TYPE.ALREADY_EXIST));
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
        String selectedItem = (String) lvMemoItems.getSelectionModel().getSelectedItem();
        LOGGER.debug("deleting item [{}]", selectedItem);
        //TODO implement delete logic here
    }

    @FXML
    void initialize() {
        LOGGER.debug("Initializing [{}]", MemoController.class.getSimpleName());
        if (this.lvMemoItems == null) {
            this.lvMemoItems = ((ViewContainerController) VCStore.getController(ViewContainerController.class))
                    .getTvMemoItems();
            this.memoItemList = FXCollections.observableArrayList();
//        TODO complete onChange listener to memoItemsList
//            memoItemList.addListener((ListChangeListener<MemoItem>) c -> {
//                if(lvMemoItems!=null)
//                    lvMemoItems.getItems().add(c.getAddedSubList().get(0).getShortDescription());
//                LOGGER.debug("changed item [{}]", c);
//            });
        }
        refresh();

    }


    @FXML
    void refresh() {
        LOGGER.debug("refreshing the addMemoView");
        memoAddedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        memoLastModifiedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        loadCategories();
        loadMemoItems();
    }

    private void loadMemoItems() {
        LOGGER.debug("loading memo items");
        memoItemList.clear();
        coreService.findAllMemoItems().forEach(memoItemList::add);
        if (lvMemoItems == null) {
            LOGGER.debug("loading lvMemoItems");
            this.lvMemoItems = ((ViewContainerController) VCStore.getController(ViewContainerController.class))
                    .getTvMemoItems();
        }
        if (lvMemoItems != null) {
            LOGGER.debug("lvMemoItems Loaded");
            lvMemoItems.getItems().clear();
            memoItemList.forEach(memoItem -> lvMemoItems.getItems()
                    .add(memoItem.getShortDescription()));
        } else {
            LOGGER.error("lvMemoItems loading failed");
        }
    }

    private void loadCategories() {
        LOGGER.debug("loading categories");
        memoCategory.getItems().clear();
        coreService.findAllCategories().forEach(category -> {
            memoCategory.getItems().add(category.getLabel());
        });
    }
}
