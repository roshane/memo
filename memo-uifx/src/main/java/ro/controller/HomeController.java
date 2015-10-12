package ro.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ducati.entity.Category;
import ro.ducati.entity.MemoItem;
import ro.ducati.service.CoreService;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

@Component
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CoreService coreService;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea memoContent;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private ComboBox<Category> memoCategory;
    @FXML
    private DatePicker memoLastModifiedDate;
    @FXML
    private DatePicker memoAddedDate;
    @FXML
    private TextField memoShortDescription;
    @FXML
    void saveItem(ActionEvent event) {
        final MemoItem memoItem = new MemoItem(memoCategory.getSelectionModel().getSelectedItem().toString(),
                memoShortDescription.getText(),
                memoAddedDate.getValue(), memoLastModifiedDate.getValue(), memoContent.getText());

        LOGGER.debug("Application save event [{}]", memoItem);
    }

    @FXML
    void resetForm(ActionEvent event) {
        System.out.println("Application reset event");
    }

    @FXML
    void deleteItem(ActionEvent event) {
        System.out.println("Application delete event");
    }

    @FXML
    void initialize() {
        assert memoContent != null : "fx:id=\"memoContent\" was not injected: check your FXML file 'home.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'home.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'home.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'home.fxml'.";
        assert memoCategory != null : "fx:id=\"memoCategory\" was not injected: check your FXML file 'home.fxml'.";
        assert memoLastModifiedDate != null : "fx:id=\"memoLastModifiedDate\" was not injected: check your FXML file 'home.fxml'.";
        assert memoAddedDate != null : "fx:id=\"memoAddedDate\" was not injected: check your FXML file 'home.fxml'.";
        assert memoShortDescription != null : "fx:id=\"memoShortDescription\" was not injected: check your FXML file 'home.fxml'.";

        memoAddedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        memoLastModifiedDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        addDummyCategories(null);
//        final ObservableList<Category> categories = FXCollections.<Category>observableArrayList();
//        addDummyCategories(categories);
//        memoCategory.setItems(categories);
    }

    private void addDummyCategories(ObservableList<Category> categories) {
        LOGGER.debug("loading categories");
        StringBuilder sb = new StringBuilder();
        coreService.findAllMemoItems().forEach(i->sb.append(i.toString()+"\n"));

        memoContent.setText(sb.toString());

    }
}
