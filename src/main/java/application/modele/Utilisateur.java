package application.modele;


import java.time.LocalDate;

public class Utilisateur {
    private String username;
    private String password;
    private LocalDate dateNaissance;
    private String lieu;

    public Utilisateur(String username, String password, LocalDate dateNaissance, String lieu) {
        this.username = username;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.lieu = lieu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "username='" + username + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", lieu='" + lieu + '\'' +
                '}';
    }
}
