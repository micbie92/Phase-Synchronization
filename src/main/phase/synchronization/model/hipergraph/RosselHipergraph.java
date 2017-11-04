package main.phase.synchronization.model.hipergraph;


import main.phase.synchronization.model.verticle.RosselVertex;
import main.phase.synchronization.model.verticle.Vertex;

/**
 * Created by Michał Bielecki on 26.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class RosselHipergraph extends BAHiperGraph{

    @Override
    protected RosselVertex createVertex() {
        return new RosselVertex();
    }
}
