package phase.synchronization.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.apache.log4j.Logger;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonsController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(ButtonsController.class);

    @FXML
    private ComboBox comboBox;

    @FXML
    private void printOutput()
    {
        LOGGER.debug("Button has been pressed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> graphList = FXCollections.observableArrayList("SimpleGraph");
        comboBox.getItems().addAll(graphList);
    }
}
