package main.phase.synchronization.model.verticle;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michał Bielecki on 17.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class Vertex{

    private static final AtomicInteger count = new AtomicInteger(0);
    private final Integer id;
    private Collection<Integer> edgeIds = new HashSet<>();

    public void postInit(){}

    public Vertex() {
        this.id=count.incrementAndGet();
    }

    public Integer getId() {
        return id;
    }

    public Collection<Integer> getEdgeIds() {
        return edgeIds;
    }

    public void addEdge(Integer edgeId) {
        this.edgeIds.add(edgeId);
    }

    @Override
    public String toString() {
        return "V:"+id;
    }
}
