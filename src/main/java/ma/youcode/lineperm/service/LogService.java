package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import ma.youcode.lineperm.model.AccessLog;

public class LogService {
    private static final Path LOG_FILE = Path.of("data/access.log");

    public LogService() {
        try {
            if (!Files.exists(LOG_FILE)) {
                Files.createFile(LOG_FILE);
            }
        } catch (Exception e) {
            System.out.println("Erreur craetion");
        }
    }

    public void log(String utilisateur, String action, String fichier, boolean resultat) {

        AccessLog accessLog = new AccessLog(utilisateur, action, fichier, resultat);

        try {
            Files.writeString(LOG_FILE, accessLog + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Erreur creation");
        }
    }

}
