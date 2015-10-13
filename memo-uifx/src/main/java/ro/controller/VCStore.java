package ro.controller;

import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.core.SpringFXMLLoader;
import ro.domain.ViewNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Create and load view and corresponding controllers from spring IOC
 *
 * @author roshanep@hsenidmobile.com
 */
public class VCStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(VCStore.class);
    private static final Map<String, Node> viewStore = new HashMap<>();

    private static final SpringFXMLLoader fxmlLoader = new SpringFXMLLoader();

    public static void loadResources(String... url) {
        for (String location : url) {
            LOGGER.debug("loading view from [{}]", location);
            Node node = (Node) fxmlLoader.load(location);
            viewStore.put(node.getId(), node);
        }
    }

    public static Node getViewById(String id) throws ViewNotFoundException {
        if (viewStore.containsKey(id)) {
            return viewStore.get(id);
        }
        throw new ViewNotFoundException();
    }

}
