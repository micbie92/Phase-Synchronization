package main.phase.synchronization.controllers.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import main.phase.synchronization.constants.CommonConstants;
import main.phase.synchronization.controllers.MainController;
import main.phase.synchronization.engine.javasim.Simulation;
import main.phase.synchronization.engine.javasim.SimulationProcess;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.hipergraph.BAHiperGraph;
import main.phase.synchronization.utils.DisplayUtils;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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
    private void startSimulation()
    {
        LOGGER.debug("startSimulation button has been pressed");
        AbstractHiperGraph graph = new BAHiperGraph();
        graph.build(Integer.parseInt(graphSizeField.getText()), Integer.parseInt(edgesPerStepField.getText()), Integer.parseInt(initGraphSizeField.getText()));
        main.setGraph(graph);
        DisplayUtils.printGraph(graph.getGraph());

        Simulation sim = new Simulation();

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

    @FXML
    @Deprecated
    public void createCustomElement(){
        Parent sp = graphTypeField.getParent().getParent();

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
}
