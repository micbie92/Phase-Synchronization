package phase.synchronization.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

public class Main extends Application {

    private static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        Configuring log4j properties
        BasicConfigurator.configure();
        PropertyConfigurator.configure("resources/log4j.properties");

//        Main application
        LOGGER.info("Started application");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        initRootLayout("phase/synchronization/view/RootLayout.fxml", primaryStage);
    }

    private void initRootLayout(String path, Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(path));

            ComboBox comboBox1 = new ComboBox();

            stage.setTitle("Phase Synchronization");
            stage.setScene(new Scene(root, 1366, 768));
            setFullScreen(stage);
            stage.show();

        } catch (IOException e) {
            LOGGER.error("ERROR while loading fmxl configuration. ", e);
        } catch(Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private void setFullScreen(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }


}
