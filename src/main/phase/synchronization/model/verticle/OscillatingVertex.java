package main.phase.synchronization.model.verticle;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class OscillatingVertex extends Vertex{

    private double phase;

    public OscillatingVertex(){
        super();
    }
    public OscillatingVertex(double phase){
        super();
        this.phase = phase;
    }

    @Override
    public String toString(){
        return super.toString() +" p:"+phase;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}
