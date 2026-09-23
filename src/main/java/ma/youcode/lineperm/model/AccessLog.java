package ma.youcode.lineperm.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AccessLog {
    private Long id;
    private Long userId;
    private Long fileId;
    private String action;
    private String fichier;
    private Boolean resultat;
    private LocalDate date = LocalDate.now();
    private String heure = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

    public AccessLog(Long userId, Long fileId, String action, String fichier, Boolean resultat) {
        this.userId = userId;
        this.fileId =fileId;
        this.action = action;
        this.fichier = fichier;
        this.resultat = resultat;
    }
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
        // return  date + ";" + heure + ";" + userId + ";" + action + ";" + fichier + ";" + (resultat ? "OK" : "REFUSED");
        return  date + ";" + heure + ";" + userId + ";" + action + ";" + fichier + ";" + (resultat ? "OK" : "REFUSED");
    }
}
