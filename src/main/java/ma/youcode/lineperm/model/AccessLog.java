package ma.youcode.lineperm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AccessLog {
    private final Long id;
    private final Long userId;
    private final Long fileId;
    private final String action;
    private final String fichier;
    private final Boolean resultat;
    private final LocalDate date = LocalDate.now();
    private final String heure = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

    public AccessLog(Long id, Long userId, Long fileId, String action, String fichier, Boolean resultat) {
        this.id=id;
        this.userId = userId;
        this.fileId =fileId;
        this.action = action;
        this.fichier = fichier;
        this.resultat = resultat;
    }
    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getFileId() {
        return fileId;
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
        return  date + ";" + heure + ";" + userId + ";" + action + ";" + fichier + ";" + (resultat ? "OK" : "REFUSED");
    }
}
