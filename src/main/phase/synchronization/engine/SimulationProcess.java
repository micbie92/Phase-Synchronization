package main.phase.synchronization.engine;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.chart.XYChart;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.verticle.OscillatingVertex;
import org.apache.log4j.Logger;

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

    private IntegerProperty step = new SimpleIntegerProperty(1);

    private boolean pause = false;

    @Override
    public void run() {
        try {
            while (true) {
                if (!pause) {
                    Thread.sleep(100);
                    makeStep();
                    step.setValue(step.getValue() + 1);
                } else {
                    LOGGER.debug("Pausing...");
                    Thread.sleep(1000);

                }
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception occured during simulation: ", e);
        }
    }

    private void makeStep() {
        LOGGER.info("Making step: " + step);
        calculateNewPhase();
        LOGGER.debug("Graph: " + this.graph);
    }

    public XYChart.Series createSeries(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("3", 3*step.getValue()));
        series.getData().add(new XYChart.Data("2", 2*step.getValue()));
        series.getData().add(new XYChart.Data("1", 1*step.getValue()));
        return series;
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

    public final IntegerProperty getStep() {
        return step;
    }
}
