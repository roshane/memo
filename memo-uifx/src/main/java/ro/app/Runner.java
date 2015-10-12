package ro.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.core.SpringFXMLLoader;

/**
 * Created by Roshane on 9/13/2015.
 */

public class Runner extends Application {

    private static final Logger LOGGER= LoggerFactory.getLogger(Runner.class);

    private final SpringFXMLLoader springFXMLLoader=new SpringFXMLLoader();

    public static void main(String[] args) {
        LOGGER.debug("Launching application args [{}]",args);
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final AnchorPane app = (AnchorPane) springFXMLLoader.load("/home.fxml");
        primaryStage.setScene(new Scene(app));
        setUserAgentStylesheet(STYLESHEET_MODENA);
        primaryStage.setTitle("Memo manager");
        primaryStage.show();
    }
}
