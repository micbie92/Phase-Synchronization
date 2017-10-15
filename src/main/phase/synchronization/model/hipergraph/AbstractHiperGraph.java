package main.phase.synchronization.model.hipergraph;

import main.phase.synchronization.model.edge.HiperEdge;
import main.phase.synchronization.model.verticle.OscillatingVertex;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public abstract class AbstractHiperGraph{

    private static Logger LOGGER = Logger.getLogger(AbstractHiperGraph.class);

    private int step = 1;
    private Set<OscillatingVertex> vertices = new HashSet<>();
    private Set<HiperEdge> hiperEdges = new HashSet<>();

    protected abstract void makeStep(int verticesPerStep, int edgeSize);

    protected abstract void initGraph(int initSize);

    public void build(int graphSize, int verticesPerStep, int initSize){
        initGraph(initSize);
        LOGGER.info("Initialized graph: "+this );
        while(vertices.size()<graphSize){
            this.makeAbstractStep(verticesPerStep, initSize);
        }
        LOGGER.info("Final graph: "+this );
        addInitPhase();
    }

    private void makeAbstractStep(int verticesPerStep, int edgeSize) {
        LOGGER.debug("Making "+step+" step.");
        makeStep(verticesPerStep, edgeSize);
        step++;
    }

    public OscillatingVertex getVerticle(Integer id) {
        OscillatingVertex toReturn = getVertices().parallelStream()
                .filter(ov -> ov.getId().equals(id))
                .findAny()
                .orElse(null);
        return toReturn;
    }

    private void addInitPhase() {
        vertices.parallelStream()
                .forEach(ov -> addPhaseToVertice(ov));
        LOGGER.info("Initial phase added");
    }

    private void addPhaseToVertice(OscillatingVertex ov) {
        double phase = ThreadLocalRandom.current().nextDouble(0.0, 360.0);
        ov.setPhase(phase);
        LOGGER.debug("Initial phase added: "+ov.getPhase()+" to verticle: "+ov);
    }

    protected OscillatingVertex addNewVerticle() {
        OscillatingVertex newVericle = new OscillatingVertex(vertices.size()+1);
        addVertex(newVericle);
        return newVericle;
    }

    protected void addHiperEdge(Set<OscillatingVertex> verticles){
        int edgeCount = hiperEdges.size();
        HiperEdge he = new HiperEdge(edgeCount+1, verticles);
        updateVerticles(verticles, he.getId());
        hiperEdges.add(he);
        LOGGER.info("New edge added: "+ he);
    }

    private void updateVerticles(Set<OscillatingVertex> verticles, int id) {
        verticles.parallelStream()
                .forEach(v -> v.addEdge(id));
    }

    protected void createInitVerticles(int initSize){
        IntStream.rangeClosed(1, initSize).sequential()
                .forEach(i-> addNewVerticle());
    }

    @Override
    public String toString(){
        return "\nGRAPH VERTICLES: \n"+ getVertices() + "\n GRAPH EDGES COUNT: \n"+ getHiperEdges().size();
    }

    public Set<HiperEdge> getHiperEdges() {
        return hiperEdges;
    }

    public Set<OscillatingVertex> getVertices(){
        return this.vertices;
    }

    public int getStep() { return step; }

    private void addVertex(OscillatingVertex newVericle) {
        vertices.add(newVericle);
    }
}
