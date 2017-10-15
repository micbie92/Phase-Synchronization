package main.phase.synchronization.model.verticle;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class OscillatingVertex {

    private Integer id;
    private double phase;

    private Collection<Integer> edgeIds = new HashSet<>();

    public OscillatingVertex(Integer id){
        this.id = id;
        this.phase = 0d;
    }

    public OscillatingVertex(Integer id, double phase){
        this.id = id;
        this.phase = phase;
    }

    @Override
    public String toString(){
        return "OV: "+id+" p:"+phase;
    }


    public Collection<Integer> getEdgeIds() {
        return edgeIds;
    }

    public void addEdge(Integer edgeId) {
        this.edgeIds.add(edgeId);
    }

    public Integer getId() {
        return id;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}
