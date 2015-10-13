package ro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    private static final Logger LOGGER= LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CoreService coreService;

    @FXML
    private Button btnSave;

    @FXML
    private TextField categoryLabel;

//    @FXML
//    private Button btnCatBack;

    @FXML
    private Button btnClear;

    @FXML
    private TextArea categoryDescription;

    @FXML
    private GridPane addCategoryView;
    private List<FormError> validationErrors;

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
        if(isValidateForm()){
            coreService.save(new Category(categoryLabel.getText()));

        }else{
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
        addCategoryView.getChildren().forEach(node -> {
            if (node instanceof TextInputControl) {
                if (StringUtils.isEmpty(((TextInputControl) node).getText())) {
                    validationErrors.add(new FormError(node, FormError.ERROR_TYPE.EMPTY_FIELD));
                }
            }
        });
        return validationErrors.size() == 0;
    }
}
