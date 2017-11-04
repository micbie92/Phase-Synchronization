package main.phase.synchronization.controllers.panes;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;
import main.phase.synchronization.engine.SimulationProcess;
import main.phase.synchronization.utils.ThreadUtils;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class RightPaneController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(RightPaneController.class);

    private SimulationProcess sp = SimulationProcess.getInstance();

    @FXML
    private ScatterChart<Double, Double> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        sp.getStep().addListener(
                (Observable i) -> {
                    sp.pause();
                    updateChart();
                    LOGGER.debug("Data has been changed.");
                    LOGGER.debug("Step "+i);
                    sp.resume();
                }
        );
    }

    @FXML
    public void updateChart(){
        XYChart.Series<Double, Double> series = sp.createSeries();
//        Czy to jeszcze potrzebne?
        Platform.runLater(
                () -> {
                LOGGER.info("UPDATE CHAR METHOD RUNNED");
                chart.getData().clear();
                chart.getData().add(series);
            }
        );
    }
}
