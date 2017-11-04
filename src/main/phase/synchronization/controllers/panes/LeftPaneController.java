package main.phase.synchronization.controllers.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.phase.synchronization.constants.CommonConstants;
import main.phase.synchronization.controllers.MainController;
import main.phase.synchronization.engine.SimulationProcess;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.hipergraph.BAHiperGraph;
import main.phase.synchronization.utils.ThreadUtils;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LeftPaneController extends Parent implements Initializable {

    private static Logger LOGGER = Logger.getLogger(LeftPaneController.class);

    private SimulationProcess sp = SimulationProcess.getInstance();


    @FXML
    private ComboBox graphTypeField;

    @FXML
    private TextField graphSizeField;

    @FXML
    private TextField initGraphSizeField;

    @FXML
    private TextField verticesPerStepField;

    @FXML
    private void startSimulation() {
        LOGGER.debug("startSimulation button clicked");
        Thread queryThread = new Thread(){
            public void run(){
                AbstractHiperGraph graph = new BAHiperGraph();
                graph.build(Integer.parseInt(graphSizeField.getText()), Integer.parseInt(verticesPerStepField.getText()), Integer.parseInt(initGraphSizeField.getText()));
                sp.setGraph(graph);
                LOGGER.info("Starting simulation...");
                sp.run();
            }
        };
        queryThread.setName(ThreadUtils.PROCESS_NAME_THREAD);
        queryThread.start();
    }

    @FXML
    private void pauseSimulation() {
        LOGGER.info("Pause button clicked");
        sp.pause();
    }

    @FXML
    private void resumeSimulation() {
        LOGGER.info("Resume button clicked");
        sp.resume();
    }

    @FXML
    private void resetSimulation() {
        LOGGER.info("Reset button clicked");
        sp.stop();
        AbstractHiperGraph graph = new BAHiperGraph();
        graph.build(Integer.parseInt(graphSizeField.getText()), Integer.parseInt(verticesPerStepField.getText()), Integer.parseInt(initGraphSizeField.getText()));
        sp.setGraph(graph);
        sp.resume();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> graphList = FXCollections.observableArrayList("Scale Free Hipregraph");
        graphTypeField.getItems().addAll(graphList);
        graphTypeField.getSelectionModel().selectFirst();

        graphSizeField.setText(String.valueOf(CommonConstants.DEFAULT_GRAPH_SIZE));
        initGraphSizeField.setText(String.valueOf(CommonConstants.DEFAULT_INIT_GRAPH_SIZE));
        verticesPerStepField.setText(String.valueOf(CommonConstants.DEFAULT_EDGES_PER_STEP));
    }
}
