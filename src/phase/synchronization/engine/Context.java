package phase.synchronization.engine;

import edu.uci.ics.jung.graph.Graph;
import org.apache.log4j.Logger;
import phase.synchronization.model.hipergraph.AbstractHiperGraph;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class Context {

    private static Logger LOGGER = Logger.getLogger(Context.class);

    private final static Context instance = new Context();

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

    public static Context getInstance() {
        return  instance;
    }

}
