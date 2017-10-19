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
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftPaneController extends Parent implements Initializable {

    private static Logger LOGGER = Logger.getLogger(LeftPaneController.class);

    public RightPaneController rpCtrl;

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
                SimulationProcess sim = SimulationProcess.getInstance();
                sim.setGraph(graph);
                LOGGER.info("Starting simulation...");
                sim.run();
            }
        };
        queryThread.start();
    }

    @FXML
    private void pauseSimulation() {
        LOGGER.info("Pause button clicked");
        SimulationProcess sim = SimulationProcess.getInstance();
        sim.setPause(true);
        LOGGER.info("Step: "+sim.getStep());


    }

    @FXML
    private void resumeSimulation() {
        LOGGER.info("Resume button clicked");
        SimulationProcess sim = SimulationProcess.getInstance();
        sim.setPause(false);
    }

    @FXML
    public void updateGraph() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("3", 3));
        series.getData().add(new XYChart.Data("2", 2));
        series.getData().add(new XYChart.Data("1", 1));
        rpCtrl.updateChart(series);
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
