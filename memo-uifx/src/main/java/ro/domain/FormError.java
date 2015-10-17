package ro.domain;

import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

import java.text.MessageFormat;

/**
 * @author roshanep@hsenidmobile.com
 */
public class FormError {

    private String errorMessage;

    public FormError(Node node, ERROR_TYPE errorType) {


        switch (errorType) {
            case EMPTY_FIELD:
                this.errorMessage = MessageFormat.format(errorType.message, node.getId());
                break;
            case ALREADY_EXIST:
                if (node instanceof TextInputControl)
                    this.errorMessage = MessageFormat
                            .format(errorType.message, ((TextInputControl) node).getText());
                break;
            default:
                break;
        }

    }

    @Override
    public String toString() {
        return errorMessage;
    }

    public enum ERROR_TYPE {
        EMPTY_FIELD("{0} cannot be empty."),
        ALREADY_EXIST("entry with description [{0}] already exist");
        private String message;

        ERROR_TYPE(String message) {
            this.message = message;
        }

    }
}
