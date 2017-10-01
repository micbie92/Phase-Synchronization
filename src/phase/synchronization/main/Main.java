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
            stage.setTitle("Phase Synchronization");
            Scene scene = setFullScreen(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            LOGGER.error("ERROR while loading fmxl configuration. ", e);
        } catch(Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    private Scene setFullScreen(Parent root) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return new Scene(root, bounds.getMaxX(), bounds.getMaxY());
    }


}
