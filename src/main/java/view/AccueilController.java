package view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import application.modele.Service.MauvaisPasswordException;
import application.modele.Service.UsernameDejaExistantException;
import application.modele.Service.UtilisateurNonTrouveException;
import application.modele.Utilisateur;
import controller.MainController;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AccueilController {
	@FXML
	private TextField connexionUsername;
	@FXML
	private PasswordField connexionPassword;
	@FXML
	private Button connexionSeConnecter;
	@FXML
	private TextField creationUsername;
	@FXML
	private PasswordField creationPassword;
	@FXML
	private DatePicker creationDate;
	@FXML
	private TextField creationLieu;
	@FXML
	private Button creationValider;
	@FXML
	private Label affichageErreur;
	


	// Event Listener on Button[#connexionSeConnecter].onAction
	@FXML
	public void connexion(ActionEvent event) {
		try {
			Utilisateur user = MainController.getInstance().connexion(connexionUsername.getText(), connexionPassword.getText());
			
			if (user.getUsername() == "police")
				ViewController.getInstance().initializeScene("PoliceConnect");
			else if (user.getUsername() != "police")
				ViewController.getInstance().initializeScene("UserConnect");
			
		} catch (UtilisateurNonTrouveException e) {
			affichageErreur.setText("Utilisateur non trouvé");
		} catch (MauvaisPasswordException e) {
			affichageErreur.setText("Mauvais mot de passe");
		}

	}
	// Event Listener on Button[#creationValider].onAction
	@FXML
	public void creationCompte(ActionEvent event) {
		try {
			MainController.getInstance().register(creationUsername.getText(), creationPassword.getText(), creationDate.getValue(), creationLieu.getText());
			ViewController.getInstance().initializeScene("UserConnect");
		} catch (UsernameDejaExistantException e) {
			affichageErreur.setText("Nom d'utilisateur déjà existant");
		}
		
	}
}
