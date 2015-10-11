package ro.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @Autowired
    private CoreService coreService;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="memoContent"
    private TextArea memoContent; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="memoCategory"
    private ComboBox<Category> memoCategory; // Value injected by FXMLLoader

    @FXML // fx:id="memoLastModifiedDate"
    private DatePicker memoLastModifiedDate; // Value injected by FXMLLoader

    @FXML // fx:id="memoAddedDate"
    private DatePicker memoAddedDate; // Value injected by FXMLLoader

    @FXML // fx:id="memoShortDescription"
    private TextField memoShortDescription; // Value injected by FXMLLoader

    @FXML
    void saveItem(ActionEvent event) {
        final MemoItem memoItem = new MemoItem(memoCategory.getSelectionModel().getSelectedItem().toString(),
                memoShortDescription.getText(),
                memoAddedDate.getValue(), memoLastModifiedDate.getValue(), memoContent.getText());

        System.out.println("Application save event "+memoItem);
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
        // This method is called by the FXMLLoader when initialization is complete
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
        StringBuilder sb = new StringBuilder();
        coreService.findAllMemoItems().forEach(i->sb.append(i.toString()+"\n"));
        memoContent.setText(sb.toString());

    }
}
