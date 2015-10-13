package ro.domain;

import javafx.scene.Node;

import java.text.MessageFormat;

/**
 * @author roshanep@hsenidmobile.com
 */
public class FormError {

    private Node node;
    private String errorMessage;

    public static final String EMPTY_FIELD = "{0} cannot be empty";

    public FormError(Node node, ERROR_TYPE errorType) {
        this.node = node;
        this.errorMessage = MessageFormat.format(errorType.message, node.getId());

    }

    @Override
    public String toString() {
        return errorMessage;
    }

    public enum ERROR_TYPE {
        EMPTY_FIELD("{0} cannot be empty.");

        private String message;

        ERROR_TYPE(String message) {
            this.message = message;
        }

    }
}
