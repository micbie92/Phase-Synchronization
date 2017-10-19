package main.phase.synchronization.model.hipergraph;

import main.phase.synchronization.model.verticle.Vertex;
import org.apache.log4j.Logger;
import main.phase.synchronization.model.verticle.OscillatingVertex;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class BAHiperGraph extends AbstractHiperGraph {

    private static Logger LOGGER = Logger.getLogger(BAHiperGraph.class);

    public BAHiperGraph() {

    }

    @Override
    protected void makeStep(int verticesPerStep, int edgeSize) {
        Set<Vertex> availableVertices = new HashSet<>(getVertices());
        Set<Vertex> newEdgeVertices = new HashSet<>();
//        Adding new verticles
        IntStream.rangeClosed(1, verticesPerStep)
                .sequential()
                .forEach(i -> newEdgeVertices.add(addNewVerticle()));
//        Adding random verticles from graph
        double probablityDivider = 2 * getStep() * verticesPerStep;
        while (newEdgeVertices.size() < edgeSize) {
            for (Vertex ov : availableVertices) {
                if(newEdgeVertices.size() >= edgeSize){
                    break;
                }
                double propablity = ov.getEdgeIds().size() / probablityDivider;
                double random = Math.random();
                LOGGER.debug("V"+ov.getId()+" prob: " + propablity + ", rand: " + random);
                if (random < propablity) {
                    LOGGER.debug("Added: ");
                    newEdgeVertices.add(ov);
                }
            }
        }
        addHiperEdge(newEdgeVertices);
    }

    @Override
    protected void initGraph(int initSize) {
        createInitVerticles(initSize);
        addHiperEdge(new HashSet<>(getVertices()));
    }
}
