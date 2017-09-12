package phase.synchronization.model.hipergraph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import org.apache.log4j.Logger;
import phase.synchronization.model.oscillator.OscillatingVertex;

import java.util.Collection;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class SimpleHiperGraph implements HiperGraphInterface {

    private static Logger LOGGER = Logger.getLogger(SimpleHiperGraph.class);

    @Override
    public Graph build(int vertexCount, int edgeSize) {
        Graph<OscillatingVertex, String> graph = new SparseMultigraph<OscillatingVertex, String>();
        Collection<OscillatingVertex> vertexCollection = createVertex(vertexCount);
        addVertexes(graph, vertexCollection);
        addEdges(graph, edgeSize);
        return graph;
    }

    private void addEdges(Graph<OscillatingVertex, String> graph, int edgeSize) {
        graph.addEdge("Edge1", new OscillatingVertex(1) ,new OscillatingVertex(3), EdgeType.DIRECTED);
    }

    private void addVertexes(Graph<OscillatingVertex, String> graph, Collection<OscillatingVertex> vertexCollection) {
        vertexCollection.parallelStream()
                .forEach(v -> graph.addVertex(v));
    }
}
