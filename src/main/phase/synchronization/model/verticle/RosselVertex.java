package main.phase.synchronization.model.verticle;

/**
 * Created by Michał Bielecki on 17.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class RosselVertex extends OscillatingVertex {

    private double x;
    private double y;

    public RosselVertex(){
        super();
    }

    public RosselVertex(double x, double y){
        super();
        this.x=x;
        this.y=y;
    }

    @Override
    public void postInit(){
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
