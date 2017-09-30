package phase.synchronization.model.oscillator;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class OscillatingVerticle {

    public Integer id;
    public double phase;
    public int edgesCount;

    public OscillatingVerticle(Integer id){
        this.id = id;
        this.phase = 0d;
        this.edgesCount = 0;
    }

    public OscillatingVerticle(Integer id, double phase){
        this.id = id;
        this.phase = phase;
        this.edgesCount = 0;
    }



    public int getEdgesCount() {
        return edgesCount;
    }

    public void setEdgesCount(int edgesCount) {
        this.edgesCount = edgesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}
