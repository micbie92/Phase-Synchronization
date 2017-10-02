package main.phase.synchronization.controllers.panes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;
import main.phase.synchronization.controllers.MainController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał Bielecki on 01.10.17.
 * Faculty of Physics
 * Warsaw University of Technology
 */
public class RightPaneController implements Initializable{

    private static Logger LOGGER = Logger.getLogger(RightPaneController.class);

    private MainController main;

    @FXML
    private ScatterChart<?, ?> chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("3", 100.2));
        series.getData().add(new XYChart.Data("2", 10.3));
        series.getData().add(new XYChart.Data("1", 0.1));

        chart.getData().add(series);
    }
}
