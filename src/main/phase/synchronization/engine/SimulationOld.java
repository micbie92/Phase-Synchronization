package main.phase.synchronization.engine;

import org.apache.log4j.Logger;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class SimulationOld {

    private static Logger LOGGER = Logger.getLogger(SimulationOld.class);

    private final static SimulationOld instance = new SimulationOld();

    private AbstractHiperGraph graph;

    private int graphSizeField;





    public AbstractHiperGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractHiperGraph graph) {
        this.graph = graph;
    }

    public int getGraphSizeField() {
        return graphSizeField;
    }

    public void setGraphSizeField(int graphSizeField) {
        this.graphSizeField = graphSizeField;
    }

    public int getInitGraphSizeField() {
        return initGraphSizeField;
    }

    public void setInitGraphSizeField(int initGraphSizeField) {
        this.initGraphSizeField = initGraphSizeField;
    }

    public int getEdgesPerStepField() {
        return edgesPerStepField;
    }

    public void setEdgesPerStepField(int edgesPerStepField) {
        this.edgesPerStepField = edgesPerStepField;
    }

    private int initGraphSizeField;

    private int edgesPerStepField;

    public static SimulationOld getInstance() {
        return  instance;
    }

}
