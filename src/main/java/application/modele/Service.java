package application.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private static Map<String, Utilisateur> utilisateurs = new HashMap<>(
            Map.of("police",new Utilisateur("police","police",null, null))
    );
    private static Map<String, Attestation> attestations = new HashMap<>();

    public Utilisateur register(String username, String password, LocalDate dateNaissance, String lieu) throws UsernameDejaExistantException {
        if (utilisateurs.containsKey(username)) {
            throw new UsernameDejaExistantException(username);
        }
        Utilisateur utilisateur = new Utilisateur(username,password,dateNaissance,lieu);
        utilisateurs.put(utilisateur.getUsername(),utilisateur);
        return utilisateur;
    }

    public Utilisateur login(String username, String password) throws UtilisateurNonTrouveException, MauvaisPasswordException {
        if (!utilisateurs.containsKey(username)) {
            throw new UtilisateurNonTrouveException(username);
        }
        Utilisateur utilisateur = utilisateurs.get(username);

        if (!utilisateur.getPassword().equals(password)) {
            throw new MauvaisPasswordException();
        }
        return utilisateur;
    }

    public String genereAttestation(String username, LocalDateTime debut, String motif) throws MauvaiseAttestationException, UtilisateurNonTrouveException {
        Objects.requireNonNull(username);

        if (motif==null||motif.isBlank()) {
            throw new MauvaiseAttestationException("pas de motif");
        }
        if (!utilisateurs.containsKey(username)) {
            throw new UtilisateurNonTrouveException(username);
        }
        Utilisateur utilisateur = utilisateurs.get(username);
        Attestation attestation = new Attestation(debut, motif);
        // si pas de date/heure de début, on prend maintenant
        if (attestation.getDebut()==null) {
            attestation.setDebut(LocalDateTime.now());
        }
        attestation.setUtilisateur(utilisateur);
        String cle = UUID.randomUUID().toString().substring(9,13).toUpperCase();

        attestation.setCode(cle);
        attestations.put(cle,attestation);
        return cle;
    }

    public List<Attestation> listeAttestationsUtilisateur(String username) {
        Objects.requireNonNull(username);
        return attestations.values().stream().filter(a -> a.getUtilisateur().getUsername().equals(username)).collect(Collectors.toList());
    }

    public Attestation verifieCode(String username, String cle) throws AttestationNonTrouveException {
        Objects.requireNonNull(username);
        if (!attestations.containsKey(cle)) {
            throw new AttestationNonTrouveException(cle);
        }
        return attestations.get(cle);
    }

    // les erreurs

    public class UsernameDejaExistantException extends Exception {
        public UsernameDejaExistantException(String username) {
            super(username);
        }
    }

    public class MauvaiseAttestationException extends Exception {
        public MauvaiseAttestationException(String erreur) {
            super(erreur);
        }
    }

    public class UtilisateurNonTrouveException extends Exception {
        public UtilisateurNonTrouveException(String username) {
            super(username);
        }
    }

    public class AttestationNonTrouveException extends Exception {
        public AttestationNonTrouveException(String cle) {
            super(cle);
        }
    }

    public class MauvaisPasswordException extends Exception {
    }

    // démo utilisation du service

    public static void main(String[] args) {
        Service service = new Service();

        try {
            // enregistrement d'un nouvel utilisateur
            service.register("fred","fred", LocalDate.now(),"Orleans");
            // connexion de cet utilisateur
            Utilisateur connecte = service.login("fred","fred");
            // génération d'une attestation et récupération du code de l'attestation
            String code = service.genereAttestation(connecte.getUsername(), LocalDateTime.now(),"examen pnt");
            System.out.println("code = " + code);
            // affichage de la liste des attestations de l'utilisateur
            service.listeAttestationsUtilisateur(connecte.getUsername()).forEach(
                System.out::println
            );

                    // connexion de la police
            Utilisateur police = service.login("police","police");
            // vérification de l'attestation à partir du code présenté
            Attestation attestation = service.verifieCode(police.getUsername(),code);
            System.out.println("verifie le code " +code + " : " + attestation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
