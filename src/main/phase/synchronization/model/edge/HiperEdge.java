package main.phase.synchronization.model.edge;

import main.phase.synchronization.model.verticle.Vertex;
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

    public HiperEdge(int id, Set<Vertex> verticles){
        this.id = id;
        this.verticles = verticles;
    }

    private int id;

    private Collection<Vertex> verticles;

    public Collection<Vertex> getVerticles() {
        return verticles;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "\nHE"+id+": "+verticles;
    }
}
