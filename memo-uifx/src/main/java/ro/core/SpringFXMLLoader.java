package ro.core;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author roshanep@hsenidmobile.com
 */
public class SpringFXMLLoader {

    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(MemoApplicationContext.class);

    public Object load(String url) {
        try (InputStream inputStream = SpringFXMLLoader.class.getResourceAsStream(url)) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(context::getBean);
            return fxmlLoader.load(inputStream);
        } catch (IOException ioexception) {
            throw new RuntimeException("Error loading application");
        }
    }
}
