package ro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ro.domain.FormError;
import ro.ducati.entity.Category;
import ro.ducati.service.CoreService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roshanep@hsenidmobile.com
 */

@Component
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CoreService coreService;

    @FXML
    private Button btnSave;

    @FXML
    private TextField categoryLabel;

    @FXML
    private Button btnClear;

    @FXML
    private TextArea categoryDescription;

    @FXML
    private ListView<String> categoryList;

    @FXML
    private GridPane addCategoryView;
    private List<FormError> validationErrors;

    private static final String DEFAULT_DESCRIPTION = "%s related notes";

    @FXML
    void clearForm(ActionEvent event) {
        addCategoryView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                ((TextInputControl) node).clear();
            }
        });
    }

    @FXML
    void saveCategory(ActionEvent event) {
        if (isValidateForm()) {
            Category category = new Category(categoryLabel.getText());
            coreService.save(category);
            categoryList.getItems().add(categoryLabel.getText());
            clearForm(new ActionEvent());
            LOGGER.debug("added category [{}]", category);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String errorMessages = validationErrors.stream()
                    .map(FormError::toString).reduce("", (a, b) -> a.concat("\n" + b));
            alert.setContentText(errorMessages);
            alert.setHeaderText(null);
            alert.show();
        }
    }

    @FXML
    void initialize() {
        LOGGER.debug("initializeing [{}]", CategoryController.class.getSimpleName());
        ObservableList<String> listViewItems = FXCollections.observableArrayList();
        coreService.findAllCategories().forEach(category -> listViewItems.add(category.getLabel()));
        categoryList.setItems(listViewItems);
    }

    private boolean isValidateForm() {
        validationErrors = new ArrayList<>();
        addCategoryView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                if (StringUtils.isEmpty(((TextInputControl) node).getText())) {
                    validationErrors.add(new FormError(node, FormError.ERROR_TYPE.EMPTY_FIELD));
                }
            }
        });
        return validationErrors.size() == 0;
    }

    public void deleteCategory(ActionEvent actionEvent) {
        if (StringUtils.isEmpty(categoryList.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select a category to delete");
            alert.setHeaderText(null);
            alert.show();
        } else {
            Category category = new Category(categoryList.getSelectionModel().getSelectedItem());
            coreService.delete(category);
            initialize();
            LOGGER.debug("deleted category [{}]", category);
        }
    }


    public void fillDescription(Event event) {
        KeyEvent keyEvent = (KeyEvent) event;
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            categoryDescription.setText(String.format(DEFAULT_DESCRIPTION, categoryLabel.getText()));
        }
    }
}
