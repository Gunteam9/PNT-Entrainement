package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import application.modele.Attestation;
import application.modele.Service;
import application.modele.Service.AttestationNonTrouveException;
import application.modele.Service.MauvaisPasswordException;
import application.modele.Service.MauvaiseAttestationException;
import application.modele.Service.UsernameDejaExistantException;
import application.modele.Service.UtilisateurNonTrouveException;
import application.modele.Utilisateur;
import view.ViewController;


public class MainController {
	
	private final Service service = new Service();
	
	private Utilisateur user;
	
	private static final MainController instance = new MainController();

	private MainController() {
        try {
        	service.register("fred","fred", LocalDate.now(),"Orleans");
			service.login("police","police");
		} catch (UtilisateurNonTrouveException | MauvaisPasswordException | UsernameDejaExistantException e) {
			e.printStackTrace();
		}
		ViewController.getInstance().start();
	}

	public Utilisateur connexion(String username, String password) throws UtilisateurNonTrouveException, MauvaisPasswordException {
		user = service.login(username, password);
		return user;
	}
	
	public Utilisateur register(String username, String password, LocalDate dateNaissance, String lieu) throws UsernameDejaExistantException {
		user = service.register(username, password, dateNaissance, lieu);
		return user;
	}
	
	public String genereAttestation(LocalDateTime debut, String motif) throws MauvaiseAttestationException {
		try {
			return service.genereAttestation(user.getUsername(), debut, motif);
		} catch (UtilisateurNonTrouveException e) {
			//Utilisateur non connecté
			ViewController.getInstance().initializeScene("Accueil");
			return null;
		}
	}
	
	public List<Attestation> getAttestations() {
		return service.listeAttestationsUtilisateur(user.getUsername());
	}
	
	public Attestation verifieCode(String username, String cle) throws AttestationNonTrouveException {
		return service.verifieCode(username, cle);
	}
	
	public void reset() {
		user = null;
	}
	
	
	public static final MainController getInstance() {
		return instance;
	}



}
