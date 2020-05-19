package view;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController {

	private static ViewController instance = new ViewController();
	
	private Stage stage = new Stage();
				
	private ViewController() {}
	
	public final void start() {
		initializeScene("Accueil");
		
		stage.setTitle("PNT Entrainement");
		stage.show();
	}

	public void initializeScene(String sceneName) {
		System.out.println("Scene a initialiser: " + sceneName);
		URL location = ViewController.class.getResource(sceneName + ".fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.setScene(new Scene(root));
	}

	public static final ViewController getInstance() {
		return instance;
	}

}
