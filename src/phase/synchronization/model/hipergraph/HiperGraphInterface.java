package phase.synchronization.model.hipergraph;

import edu.uci.ics.jung.graph.Graph;
import phase.synchronization.model.oscillator.OscillatingVertex;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public interface HiperGraphInterface {

    Graph build(int vertex, int edgeSize);

    default Collection<OscillatingVertex> createVertex(int vertexCount){
        double phase = 0d;
        Collection<OscillatingVertex> collection = Collections.EMPTY_LIST;
        for(int i=1;1<=vertexCount;i++){
            OscillatingVertex ov = new OscillatingVertex(i,phase);
            collection.add(ov);
        }
//        IntStream.rangeClosed(1,vertexCount)
//                .sequential(i -> collection.add(new OscillatingVertex(i,phase)));
        return collection;
    }
}
