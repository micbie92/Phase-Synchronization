package main.phase.synchronization.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class MainController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(MainController.class);

//    private final static MainController instance = new MainController();

    @FXML
    Parent leftPaneController;

    @FXML
    Parent rightPaneController;

    private AbstractHiperGraph graph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.info("Main controller has been initialized!");
    }

    public AbstractHiperGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractHiperGraph graph) {
        this.graph = graph;
    }
}
