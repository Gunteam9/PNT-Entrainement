package view;

import java.time.LocalDateTime;

import application.modele.Attestation;
import application.modele.Service.MauvaiseAttestationException;
import controller.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserConnectController {
	@FXML
	private DatePicker date;
	@FXML
	private TextField heure;
	@FXML
	private TextField minute;
	@FXML
	private TextField motif;
	@FXML
	private TableView<Attestation> listeTable;
	@FXML
	private Label affichageErreur;

	// Event Listener on Button.onAction
	@FXML
	public void enregistrerAttestation(ActionEvent event) {
		LocalDateTime time = null;
		try {
			time = date.getValue().atTime(Integer.valueOf(heure.getText()), Integer.valueOf(minute.getText()));
			MainController.getInstance().genereAttestation(time, motif.getText());
			initialize();
		} catch (NumberFormatException e) {
			affichageErreur.setText("L'heure saisie n'est pas correcte");
		} catch (MauvaiseAttestationException e) {
			affichageErreur.setText("L'attestation n'est pas correcte");
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void retour(ActionEvent event) {
		MainController.getInstance().reset();
		ViewController.getInstance().initializeScene("Accueil");
	}
	
	//INJECT
	@SuppressWarnings("unchecked")
	public void initialize() {
		TableColumn<Attestation, String> codeColumn = (TableColumn<Attestation, String>) listeTable.getColumns().get(0);
		TableColumn<Attestation, String> dateColumn = (TableColumn<Attestation, String>) listeTable.getColumns().get(1);
		TableColumn<Attestation, String> motifColumn = (TableColumn<Attestation, String>) listeTable.getColumns().get(2);
		
		ObservableList<Attestation> list = FXCollections.observableArrayList(MainController.getInstance().getAttestations());
		
		codeColumn.setCellValueFactory(new PropertyValueFactory<Attestation, String>("code"));
		dateColumn.setCellValueFactory(o -> { 
			return new SimpleStringProperty(o.getValue().getDebut().toString());
		});
		motifColumn.setCellValueFactory(new PropertyValueFactory<Attestation, String>("motif"));
		
		listeTable.setItems(list);
	}
}
