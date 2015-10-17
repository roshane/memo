package ro.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.controller.VCStore;

/**
 * Created by Roshane on 9/13/2015.
 */

public class Runner extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        LOGGER.debug("Launching application args [{}]", new Object[]{args});
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VCStore.loadResources("/memo.fxml", "/category.fxml","/container.fxml");
        primaryStage.setScene(new Scene((AnchorPane) VCStore.getViewById("containerView")));
        primaryStage.setTitle("Memo manager");
        primaryStage.show();
    }
}
