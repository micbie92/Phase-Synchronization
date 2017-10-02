package main.phase.synchronization.utils;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

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

    public static void printGraph(Graph graph){
        //        Print Graph
        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Integer, String> layout = new CircleLayout(graph);

        layout.setSize(new Dimension((int) getScreenWidth() ,(int) getScreenHeight())); // sets the initial size of the space

        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension((int) getScreenWidth() ,(int) getScreenHeight())); //Sets the viewing area size

        JFrame frame = new JFrame(graph.getClass().getSimpleName());
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

}
