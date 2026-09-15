package ma.youcode.lineperm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AccessLog {
    private final String utilisateur;
    private final String action;
    private final String fichier;
    private final Boolean resultat;
    private final LocalDate date = LocalDate.now();
    private final String heure = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

    public AccessLog(String utilisateur, String action, String fichier, Boolean resultat) {
        this.utilisateur = utilisateur;
        this.action = action;
        this.fichier = fichier;
        this.resultat = resultat;
    }
    public String getUtilisateur() {
        return utilisateur;
    }

    public String getAction() {
        return action;
    }

    public String getFichier() {
        return fichier;
    }

    public Boolean getResultat() {

        return resultat;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String toString() {
        return  date + ";" + heure + ";" + utilisateur + ";" + action + ";" + fichier + ";" + (resultat ? "OK" : "REFUSED");
    }
}
