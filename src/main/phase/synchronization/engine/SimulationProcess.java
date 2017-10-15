package main.phase.synchronization.engine;

import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.verticle.OscillatingVertex;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class SimulationProcess implements Runnable {

    private static Logger LOGGER = Logger.getLogger(SimulationProcess.class);

    private final static SimulationProcess instance = new SimulationProcess();

    private SimulationProcess() {}

    private AbstractHiperGraph graph;

    private boolean pause = false;

    private int step = 1;

    @Override
    public void run() {
        while (true) {
            try {
                if (!pause) {
                    Thread.sleep(1000);
                    makeStep(step);
                    step++;
                } else {
                    LOGGER.info("Pausing...");
                    Thread.sleep(2000);

                }
            } catch (InterruptedException e) {
                LOGGER.error("Exception occured during simulation: ", e);
            }
        }
    }

    private void makeStep(int step) {
        LOGGER.info("Making step: " + step);
        Collection<OscillatingVertex> verticles = graph.getVertices();
        AbstractHiperGraph newGraph = graph;
        verticles.stream()
                .forEach(ov -> calculateNewPhase(ov, newGraph));
        LOGGER.debug("Old graph: " + graph);
        LOGGER.debug("New graph: " + newGraph);
    }

    private void calculateNewPhase(OscillatingVertex ov, AbstractHiperGraph newGraph) {
        double newPhase = ov.getPhase() + 1.0;
        newGraph.getVerticle(ov.getId()).setPhase(newPhase);
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
