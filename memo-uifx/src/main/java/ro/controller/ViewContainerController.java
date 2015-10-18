package ro.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.domain.ViewNotFoundException;


/**
 * @author roshanep@hsenidmobile.com
 */
@Component
public class ViewContainerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewContainerController.class);
    @FXML
    private Button btnViewAddMemo;

    @FXML
    private Button btnViewAddCategory;

    @FXML
    private AnchorPane containerView;

    @FXML
    private AnchorPane actionViewContainer;
    @FXML
    private ListView lvMemoItems;

    @FXML
    void viewAddMemoView(ActionEvent event) {
        changeView("addMemoView");
    }

    @FXML
    void viewAddCategory(ActionEvent event) {
        changeView("addCategoryView");
    }

    @FXML
    void initialize() {
        try {
            actionViewContainer.getChildren().add(VCStore.getViewById("addMemoView"));
        } catch (ViewNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void changeView(String viewId) {
        if (actionViewContainer.getChildren().size() > 0) {
            actionViewContainer.getChildren().remove(0);
            try {
                GridPane gridPane = (GridPane) VCStore.getViewById(viewId);
                FadeTransition ft = new FadeTransition(Duration.millis(300), gridPane);
                ft.setFromValue(0.1);
                ft.setToValue(1);
                ft.setAutoReverse(false);
                actionViewContainer.getChildren().add(gridPane);
                ft.play();
            } catch (ViewNotFoundException e) {
                LOGGER.error("error : {}", e);
            }
        }
    }

    public ListView getTvMemoItems() {
        return lvMemoItems;
    }

}
