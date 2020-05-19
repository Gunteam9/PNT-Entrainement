package view;

import application.modele.Attestation;
import application.modele.Service.AttestationNonTrouveException;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PoliceConnectController {
	@FXML
	private Label attestationDebut;
	@FXML
	private Label attestationMotif;
	@FXML
	private TextField username;
	@FXML
	private Label date;
	@FXML
	private Label lieu;
	@FXML
	private TextField code;
	@FXML
	private Label affichageErreur;

	// Event Listener on Button.onAction
	@FXML
	public void control(ActionEvent event) {
		Attestation attestation;
		try {
			attestation = MainController.getInstance().verifieCode(username.getText(), code.getText());
			
			attestationDebut.setText(attestation.getDebut().toString());
			attestationMotif.setText(attestation.getMotif());
		
			date.setText(attestation.getUtilisateur().getDateNaissance().toString());
			lieu.setText(attestation.getUtilisateur().getLieu());
			
		} catch (AttestationNonTrouveException e) {
			affichageErreur.setText("Attestation non trouvé");
		}
	}
	
	
	// Event Listener on Button.onAction
	@FXML
	public void retour(ActionEvent event) {
		MainController.getInstance().reset();
		ViewController.getInstance().initializeScene("Accueil");
	}
	
}
