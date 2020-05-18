package application.modele;

import java.time.LocalDateTime;

public class Attestation {
    private LocalDateTime debut;
    private String motif;
    private Utilisateur utilisateur;
    private String code;

    public Attestation(LocalDateTime debut, String motif) {
        this.debut = debut;
        this.motif = motif;
        this.utilisateur = null;
        this.code = null;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Attestation{" +
                "debut=" + debut +
                ", motif='" + motif + '\'' +
                ", utilisateur=" + utilisateur +
                ", code='" + code + '\'' +
                '}';
    }
}
