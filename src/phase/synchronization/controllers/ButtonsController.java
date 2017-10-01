package phase.synchronization.controllers;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.apache.log4j.Logger;
import phase.synchronization.model.hipergraph.AbstractHiperGraph;
import phase.synchronization.model.hipergraph.BAHiperGraph;
import phase.synchronization.utils.DisplayUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ButtonsController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(ButtonsController.class);

    @FXML
    private ComboBox comboBox;

    @FXML
    private void startSimulation()
    {
        LOGGER.debug("startSimulation button has been pressed");
//        createCustomElement();
        AbstractHiperGraph graph = new BAHiperGraph();
        graph.build(0, 2 , 4);
        DisplayUtils.printGraph(graph.getGraph());
    }


    @FXML
    @Deprecated
    public void createCustomElement(){
        Parent sp = comboBox.getParent().getParent();

//        AnchorPane ap2 = (AnchorPane) sp. lookup("#ap2");
        AnchorPane ap1 = (AnchorPane) sp. lookup("#ap1");
        Label label = new Label();
        label.setText("Test!!!!!");
        label.setTextFill(Color.RED);
        double random = ThreadLocalRandom.current().nextDouble(0.0, 500.0);
        label.setLayoutY(random);
        random = ThreadLocalRandom.current().nextDouble(0.0, 800.0);
        label.setLayoutX(random);
        ap1.getChildren().add(label);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> graphList = FXCollections.observableArrayList("Scale Free Hipregraph");
        comboBox.getItems().addAll(graphList);
        comboBox.getSelectionModel().selectFirst();
    }
}
