package application;

import java.io.IOException;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    	MainController.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }

}
