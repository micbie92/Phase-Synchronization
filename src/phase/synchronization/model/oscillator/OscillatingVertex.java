package phase.synchronization.model.oscillator;

/**
 * Created by Michał Bielecki on 21.08.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class OscillatingVertex {

    public Integer index;
    public double phase;

    public OscillatingVertex(Integer index, double phase){
        this.index = index;
        this.phase = phase;
    }

    public OscillatingVertex(Integer index){
        this.index = index;
        this.phase = 0d;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}
