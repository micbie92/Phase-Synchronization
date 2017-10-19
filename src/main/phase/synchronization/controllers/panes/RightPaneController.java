package main.phase.synchronization.controllers.panes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;
import main.phase.synchronization.controllers.MainController;
import main.phase.synchronization.engine.SimulationProcess;
import main.phase.synchronization.model.hipergraph.AbstractHiperGraph;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class RightPaneController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(RightPaneController.class);

//    private MainController main;

private ObservableList<Pair<DoubleProperty, DoubleProperty>> graphData = FXCollections.observableArrayList();

//    private ObservableList<Property<DoubleProperty>> xData = FXCollections.observableArrayList();
//    private ObservableList<Property<DoubleProperty>> yData = FXCollections.observableArrayList();

    public RightPaneController(){}

    @FXML
    private ScatterChart<Double, Double> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimulationProcess sp = SimulationProcess.getInstance();
//        xData = sp.getXData();
//        yData = sp.getYData();
        graphData = sp.getGraphData();

        graphData.addListener(new ListChangeListener<Pair<DoubleProperty, DoubleProperty>>() {
            @Override
            public void onChanged(Change<? extends Pair<DoubleProperty, DoubleProperty>> c) {
                LOGGER.info("DATA HAS BEEEN CHANGESDDD!!!!!");
            }
        });

        //        XYChart.Series series = new XYChart.Series();
//        series.getData().add(new XYChart.Data("3", 100.2));
//        series.getData().add(new XYChart.Data("2", 10.3));
//        series.getData().add(new XYChart.Data("1", 0.1));
//        chart.getData().add(series);
    }

    public void updateChart(XYChart.Series series){
        chart.getData().clear();
        chart.getData().add(series);
    }
}
