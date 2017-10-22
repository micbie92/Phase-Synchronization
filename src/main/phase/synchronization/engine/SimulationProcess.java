package main.phase.synchronization.engine;

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

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    private SimulationProcess() {}

    private final static SimulationProcess instance = new SimulationProcess();

    private AbstractHiperGraph graph;

    private IntegerProperty step = new SimpleIntegerProperty(1);

    private boolean pause = false;

    @Override
    public void run() {

        while (running){
            synchronized (pauseLock) {
                if (!running) { // may have changed while waiting to
                    // synchronize on pauseLock
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) { // running might have changed since we paused
                        break;
                    }
                }
            }
            // Your code here
            makeStep();
            step.setValue(step.getValue()+1);
            LOGGER.info("My code here section");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error("Exception occured: ",e);
            }
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

    public void stop() {
        running = false;
        // you might also want to interrupt() the Thread that is
        // running this Runnable, too, or perhaps call:
        resume();
        // to unblock
    }

    public void pause() {
        // you may want to throw an IllegalStateException if !running
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }

    public AbstractHiperGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractHiperGraph graph) {
        this.graph = graph;
    }

    public static SimulationProcess getInstance() {
        return instance;
    }

    public final IntegerProperty getStep() {
        return step;
    }
}
