package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;


import java.io.IOException;

import controller.MainController;

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
