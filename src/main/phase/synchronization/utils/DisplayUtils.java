package main.phase.synchronization.utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Screen;
import main.phase.synchronization.controllers.panes.RightPaneController;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;


/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class DisplayUtils {

    public static double getScreenWidth(){
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return bounds.getWidth();
    }

    public static double getScreenHeight(){
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return bounds.getHeight();
    }
}
