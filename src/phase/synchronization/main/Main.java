package phase.synchronization.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        initRootLayout("../view/RootLayout.fxml", primaryStage);

    }

    private void initRootLayout(String path, Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Phase Synchronization");
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
