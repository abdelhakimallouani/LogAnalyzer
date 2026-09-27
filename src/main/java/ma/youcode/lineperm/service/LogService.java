package ma.youcode.lineperm.service;

import java.lang.classfile.ClassFile.Option;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ma.youcode.lineperm.dao.LogDao;
import ma.youcode.lineperm.model.AccessLog;

public class LogService {
    private final LogDao logDao;

    public LogService() {
        this.logDao = new LogDao();
    }

    public void countActions() {
        int total = logDao.countTotalActions();
        System.out.println("Nombre total d'actions : " + total);
    }

    public void countRefused() {
        int refused = logDao.countRefusedActions();

        System.out.println("Nombre d'acces refuses : " + refused);
    }

    public void countDistinctUsers() {
        int users = logDao.countDistinctUsers();

        System.out.println("Nombre d'utilisateurs distincts : " + users);
    }

    // public Map<String, Long> actionsByUser() {
    // return logs.stream().collect(Collectors.groupingBy(AccessLog::getUtilisateur,
    // Collectors.counting()));
    // }

    // public Map<String, Long> top3Files() {
    // return logs.stream().collect(Collectors.groupingBy(AccessLog::getFichier,
    // Collectors.counting()));
    // }

    // public List<AccessLog> refusedByUser(String username) {
    // return logs.stream().filter(log ->
    // log.getUtilisateur().equalsIgnoreCase(username) && !log.getResultat())
    // .toList();
    // }

    // public Optional<String> showMostActiveUser() {
    // return logs.stream().collect(Collectors.groupingBy(AccessLog::getUtilisateur,
    // Collectors.counting())).entrySet()
    // .stream().max(Map.Entry.comparingByValue())
    // .map(entry -> entry.getKey() + " (" + entry.getValue() + " actions)");
    // }

    // public Map<String, Long> actionsByType() {
    // return logs.stream().collect(Collectors.groupingBy(AccessLog::getAction,
    // Collectors.counting()));
    // }
}
