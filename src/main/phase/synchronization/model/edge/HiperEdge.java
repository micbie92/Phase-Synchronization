package main.phase.synchronization.model.edge;

import main.phase.synchronization.model.verticle.OscillatingVertex;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Michał Bielecki on 10.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class HiperEdge {

    private static Logger LOGGER = Logger.getLogger(HiperEdge.class);

    public HiperEdge(int id, Set<OscillatingVertex> verticles){
        this.id = id;
        this.verticles = verticles;
    }

    private int id;

    private Collection<OscillatingVertex> verticles;

    public Collection<OscillatingVertex> getVerticles() {
        return verticles;
    }

    public int getId(){
        return id;
    }
}
