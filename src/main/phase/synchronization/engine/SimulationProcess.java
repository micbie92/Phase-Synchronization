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
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class SimulationProcess extends AbstractProcess<SimulationProcess> {

    private static Logger LOGGER = Logger.getLogger(SimulationProcess.class);

    private SimulationProcess() { }

    private final static SimulationProcess instance = new SimulationProcess();

    private AbstractHiperGraph graph;

    public static SimulationProcess getInstance(){
        return instance;
    }

    @Override
    protected void makeStep() {
        Set<OscillatingVertex> vertices = graph.getVertices();
        Set<OscillatingVertex> oldVerticles = new HashSet<OscillatingVertex>(vertices);
        vertices.parallelStream()
                .forEach(v -> calculateVertexPhase((OscillatingVertex)v));
    }

    public XYChart.Series createSeries(){
        XYChart.Series series = new XYChart.Series();
        graph.getVertices().stream()
                .forEach(v -> series.getData().add(new XYChart.Data(v.getId().toString(), Double.valueOf(v.getPhase()))));
        return series;
    }

    private void calculateVertexPhase(OscillatingVertex vertex) {
        Collection<Integer> vertexEdges = vertex.getEdgeIds();
        vertex.setPhase(vertex.getPhase()+ ThreadLocalRandom.current().nextDouble(-5, 5));
    }

    public AbstractHiperGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractHiperGraph graph) {
        this.graph = graph;
    }

}
