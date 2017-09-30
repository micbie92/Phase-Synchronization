package phase.synchronization.model.hipergraph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import phase.synchronization.model.oscillator.OscillatingVerticle;

import java.util.Collection;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public abstract class AbstractHiperGraph {

    public Graph<OscillatingVerticle, String> graph = new SparseMultigraph<>();

    void build(int steps, int edgeSize, int initSize){
        initGraph(initSize);

    }

    abstract void initGraph(int initSize);

//    Method is used to filter init verticles. Return only verticles that are not neightbur of parameter vertex
    public Collection<OscillatingVerticle> getAvailableVerticles(OscillatingVerticle oscilatingVerticle) {
        Collection<OscillatingVerticle> verticles = graph.getVertices();
        verticles.remove(oscilatingVerticle);
        for (OscillatingVerticle ov: verticles) {
            if(areNeighbors(ov, oscilatingVerticle)){
                verticles.remove(ov);
            }
        }
        return verticles;
    }

    public boolean areNeighbors(OscillatingVerticle ov, OscillatingVerticle oscilatingVerticle) {
        Collection<OscillatingVerticle> neightbors = graph.getNeighbors(ov);
        return neightbors.contains(oscilatingVerticle);
    }

    public void createInitVerticles(int initSize){
        for(int i=1;1<=initSize;i++){
            OscillatingVerticle ov = new OscillatingVerticle(i);
            graph.addVertex(ov);
        }

//        IntStream.rangeClosed(1,initSize)
//                .sequential(i -> graph.addVertex(new OscillatingVerticle((i))));

    }
}
