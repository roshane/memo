package ro.controller;

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

    private List<FormError> validationErrors;

    @FXML
    void saveItem(ActionEvent event) {
        if (isValidateForm()) {
            final MemoItem memoItem = new MemoItem(
                    memoCategory.getSelectionModel().getSelectedItem(),
                    memoShortDescription.getText(),
                    memoAddedDate.getValue(),
                    memoLastModifiedDate.getValue(),
                    memoContent.getText());
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

    private boolean isValidateForm() {
        validationErrors = new ArrayList<>();
        addMemoView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                if (StringUtils.isEmpty(((TextInputControl) node).getText())) {
                    validationErrors.add(new FormError(node, FormError.ERROR_TYPE.EMPTY_FIELD));
                }
            }
        });
        return validationErrors.size() == 0;
    }

    @FXML
    void resetForm(ActionEvent event) {
        addMemoView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                ((TextInputControl) node).clear();
            }
        });
    }

    @FXML
    void deleteItem(ActionEvent event) {
        System.out.println("Application delete event");
    }

    @FXML
    void initialize() {
        assert memoContent != null : "fx:id=\"memoContent\" was not injected: check your FXML file 'home.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'home.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'home.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'home.fxml'.";
        assert memoCategory != null : "fx:id=\"memoCategory\" was not injected: check your FXML file 'home.fxml'.";
        assert memoLastModifiedDate != null : "fx:id=\"memoLastModifiedDate\" was not injected: check your FXML file 'home.fxml'.";
        assert memoAddedDate != null : "fx:id=\"memoAddedDate\" was not injected: check your FXML file 'home.fxml'.";
        assert memoShortDescription != null : "fx:id=\"memoShortDescription\" was not injected: check your FXML file 'home.fxml'.";

        memoAddedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        memoLastModifiedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        loadCategories();
    }

    private void loadCategories() {
        coreService.findAllCategories().forEach(category -> {
            memoCategory.getItems().add(category.getLabel());
        });
    }
}
