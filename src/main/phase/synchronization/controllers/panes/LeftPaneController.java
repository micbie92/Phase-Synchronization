package main.phase.synchronization.controllers.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.phase.synchronization.constants.CommonConstants;
import main.phase.synchronization.controllers.MainController;
import main.phase.synchronization.engine.SimulationProcess;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.hipergraph.BAHiperGraph;
import main.phase.synchronization.utils.DisplayUtils;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftPaneController implements Initializable {

    private static Logger LOGGER = Logger.getLogger(LeftPaneController.class);

    private MainController main;

    @FXML
    private ComboBox graphTypeField;

    @FXML
    private TextField graphSizeField;

    @FXML
    private TextField initGraphSizeField;

    @FXML
    private TextField edgesPerStepField;

    @FXML
    private void startSimulation() {
        LOGGER.debug("startSimulation button clicked");
        Thread queryThread = new Thread(){
            public void run(){
                AbstractHiperGraph graph = new BAHiperGraph();
                graph.build(Integer.parseInt(graphSizeField.getText()), Integer.parseInt(edgesPerStepField.getText()), Integer.parseInt(initGraphSizeField.getText()));
//        main.setGraph(graph);
                DisplayUtils.printGraph(graph.getGraph());
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
        LOGGER.info("RESUMED GRAPH "+sim.getGraph().getGraph().getVertices().size());
//        Thread queryThread = new Thread() {
//            public void run() {
//                sim.run();
//            }
//        };
//        queryThread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> graphList = FXCollections.observableArrayList("Scale Free Hipregraph");
        graphTypeField.getItems().addAll(graphList);
        graphTypeField.getSelectionModel().selectFirst();

        graphSizeField.setText(String.valueOf(CommonConstants.DEFAULT_GRAPH_SIZE));
        initGraphSizeField.setText(String.valueOf(CommonConstants.DEFAULT_INIT_GRAPH_SIZE));
        edgesPerStepField.setText(String.valueOf(CommonConstants.DEFAULT_EDGES_PER_STEP));
    }
}
