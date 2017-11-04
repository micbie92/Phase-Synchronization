package main.phase.synchronization.engine;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.chart.XYChart;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import main.phase.synchronization.model.verticle.OscillatingVertex;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Michał Bielecki on 24.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public abstract class AbstractProcess<T> implements Runnable{

    private static Logger LOGGER = Logger.getLogger(AbstractProcess.class);

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
    private IntegerProperty step = new SimpleIntegerProperty(1);

    protected abstract void makeStep();

    @Override
    public void run() {

        while (running){
            synchronized (pauseLock) {
                if (!running) { // may have changed while waiting to
                    // synchronize on pauseLock
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) { // running might have changed since we paused
                        break;
                    }
                }
            }
            LOGGER.info("Making step: " + step);
            makeStep();
            step.setValue(step.getValue()+1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error("Exception occured: ",e);
            }
        }
    }

    public void stop() {
        pause();
        step.setValue(1);
    }

    @Deprecated
    public void stopOld() {
        running = false;
        // you might also want to interrupt() the Thread that is
        // running this Runnable, too, or perhaps call:
        resume();
        // to unblock
    }

    public void pause() {
        // you may want to throw an IllegalStateException if !running
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }

    public final IntegerProperty getStep() {
        return step;
    }
}
