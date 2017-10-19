package main.phase.synchronization.engine;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import main.phase.synchronization.controllers.panes.RightPaneController;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.verticle.OscillatingVertex;
import main.phase.synchronization.model.verticle.Vertex;
import main.phase.synchronization.utils.DisplayUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class SimulationProcess implements Runnable {

    private static Logger LOGGER = Logger.getLogger(SimulationProcess.class);

    private SimulationProcess() {}

    private final static SimulationProcess instance = new SimulationProcess();

    private AbstractHiperGraph graph;

    public ObservableList<Pair<DoubleProperty, DoubleProperty>> getGraphData() {
        return graphData;
    }

    private ObservableList<Pair<DoubleProperty, DoubleProperty>> graphData = FXCollections.observableArrayList();

    private boolean pause = false;

    private int step = 1;

    @Override
    public void run() {
        while (true) {
            try {
                if (!pause) {
                    Thread.sleep(100);
                    makeStep(step);
                    step++;
                } else {
                    LOGGER.debug("Pausing...");
                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                LOGGER.error("Exception occured during simulation: ", e);
            }
        }
    }

    private void makeStep(int step) {
        LOGGER.info("Making step: " + step);
        DoubleProperty p1 = new SimpleDoubleProperty(step);
        DoubleProperty p2 = new SimpleDoubleProperty(step+1);
        graphData.add(new Pair<>(p1,p2));
        calculateNewPhase();
        updateChart();
        LOGGER.debug("Graph: " + this.graph);
    }

    private void updateChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("3", 3));
        series.getData().add(new XYChart.Data("2", 2));
        series.getData().add(new XYChart.Data("1", 1));
//        rpCtrl.updateChart(series);
    }

    private void calculateNewPhase() {
        Set<OscillatingVertex> vertices = graph.getVertices();
        Set<OscillatingVertex> oldVerticles = new HashSet<OscillatingVertex>(vertices);
        vertices.parallelStream()
                .forEach(v -> calculateVertexPhase((OscillatingVertex)v));
    }

    private void calculateVertexPhase(OscillatingVertex vertex) {
        Collection<Integer> vertexEdges = vertex.getEdgeIds();
        vertex.setPhase(vertex.getPhase()+1);
    }

    public AbstractHiperGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractHiperGraph graph) {
        this.graph = graph;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public static SimulationProcess getInstance() {
        return instance;
    }

    public int getStep() {
        return step;
    }
}
