package ro.domain;

/**
 * @author roshanep@hsenidmobile.com
 */
public class ViewNotFoundException extends Exception {

    private static final String MESSAGE = "View Not Found";

    public ViewNotFoundException(String id) {
        super(MESSAGE+" id");
    }
}
