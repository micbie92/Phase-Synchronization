package main.phase.synchronization.model.hipergraph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import main.phase.synchronization.model.common.Pair;
import org.apache.log4j.Logger;
import main.phase.synchronization.model.common.Pair;
import main.phase.synchronization.model.oscillator.OscillatingVerticle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public abstract class AbstractHiperGraph {

    private static Logger LOGGER = Logger.getLogger(AbstractHiperGraph.class);

    private final static String EDGE_NAME = "Edge ";

    protected Graph<OscillatingVerticle, String> graph = new SparseMultigraph<>();

    protected abstract void addEdgesToNewVerticle(OscillatingVerticle newVerticle, int newEdgesCount, int stepIndex);

    protected abstract void initGraph(int initSize);


    public void build(int graphSize, int newEdgesCount, int initSize){
        initGraph(initSize);
        LOGGER.info("Initialized graph: "+graph );
        IntStream.rangeClosed(1, graphSize-initSize).sequential()
                .forEach(i -> makeStep(newEdgesCount, i));
        LOGGER.info("Final graph: "+graph );
        addPhase();
    }

    private void addPhase() {
        graph.getVertices().parallelStream()
                .forEach(ov -> addPhaseToVerticle(ov));
        LOGGER.info("Phase added");
    }

    private void addPhaseToVerticle(OscillatingVerticle ov) {
        double phase = ThreadLocalRandom.current().nextDouble(0.0, 360.0);
        ov.setPhase(phase);
        LOGGER.debug("Added phase: "+ov.getPhase()+" to verticle: "+ov);
    }

    private void makeStep(int newEdgesCount, int stepIndex) {
        OscillatingVerticle newVerticle = addNewVerticle();
        LOGGER.debug("Making "+stepIndex+" step, for vericle: "+newVerticle.getId());
        addEdgesToNewVerticle(newVerticle, newEdgesCount, stepIndex);
    }

    private OscillatingVerticle addNewVerticle() {
        OscillatingVerticle newVericle = new OscillatingVerticle(graph.getVertexCount()+1);
        graph.addVertex(newVericle);
        return newVericle;
    }

//    Method is used to filter init verticles. Return only verticles that are not neightbur of parameter vertex
    public Collection<OscillatingVerticle> getAvailableVerticles(OscillatingVerticle oscilatingVerticle) {
        Collection<OscillatingVerticle> verticles = graph.getVertices();
        ArrayList<OscillatingVerticle> list = new ArrayList<OscillatingVerticle>();

        for (OscillatingVerticle ov: verticles) {
            if(!ov.equals(oscilatingVerticle) && !areNeighbors(ov, oscilatingVerticle)){
                list.add(ov);
            }
        }
        return list;
    }

    public boolean areNeighbors(OscillatingVerticle ov, OscillatingVerticle oscilatingVerticle) {
        Collection<OscillatingVerticle> neightbors = graph.getNeighbors(ov);
        return neightbors.contains(oscilatingVerticle);
    }

    public void createEdge(Pair<OscillatingVerticle, OscillatingVerticle> pair){
        int edgeCount = graph.getEdgeCount();
        graph.addEdge(EDGE_NAME+String.valueOf(edgeCount+1), pair.getLeft(), pair.getRight());
    }

    public void createInitVerticles(int initSize){
        IntStream.rangeClosed(1, initSize).sequential()
                .forEach(i-> addNewVerticle());
    }

    @Override
    public String toString(){
        return "\nGRAPH VERTICLES: \n"+ graph.getVertices() + "\n GRAPH EDGES: \n"+graph.getEdges();
    }

    public Graph<OscillatingVerticle, String> getGraph() {
        return graph;
    }
}
