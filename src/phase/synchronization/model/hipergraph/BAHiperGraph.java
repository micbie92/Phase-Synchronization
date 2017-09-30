package phase.synchronization.model.hipergraph;

import org.apache.log4j.Logger;
import phase.synchronization.model.oscillator.OscillatingVerticle;

import java.util.Collection;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class BAHiperGraph extends AbstractHiperGraph {

    private static Logger LOGGER = Logger.getLogger(BAHiperGraph.class);

    @Override
    void initGraph(int initSize) {
        createInitVerticles(initSize);
        createInitEdges(initSize-1);
    }

    private void createInitEdges(int edgesCount) {
        graph.getVertices().stream()
                .forEach(ov -> addInitEdges(edgesCount, ov));
    }

    private void addInitEdges(int edgesCount, OscillatingVerticle ov) {
        Collection<OscillatingVerticle> availableVertex = getAvailableVerticles(ov);
    }




//    private void addInitEdges(int edgeSize) {
////        Resolve avilable vertex
//        List<OscillatingVerticle> vertexs = getAvailableVertexs(4);
//        edge = createEdge(vertexs);
////        graph.addEdge("Edge1", EdgeType.DIRECTED);
//    }

//
//    private void createEdge(List<OscillatingVerticle> vertexs) {
//    }
//
//    //    FIXME: Przeniesc do Utils lub pomyśleć
//    public List<OscillatingVerticle> getAvailableVertexs(int edgeSize){
//        Collection<OscillatingVerticle> allVertex = graph.getVertices();
//        List<OscillatingVerticle> vertexs = allVertex.parallelStream()
//                .filter( Predicates.isAvailableVertex(edgeSize) )
//                .limit(2)
//                .collect( Collectors.toList() );
//        return vertexs;
//    }
//
//    private void addVertexes(Graph<OscillatingVerticle, String> graph, Collection<OscillatingVerticle> vertexCollection) {
//        vertexCollection.parallelStream()
//                .forEach(v -> graph.addVertex(v));
//    }


}
