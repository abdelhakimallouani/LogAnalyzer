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

    public void actionsByUser() {
        logDao.actionsByUser();
    }

    public void top3Files() {
        logDao.top3Files();
    }

    public void refusedByUser(String username) {
        logDao.refusedByUser(username);
    }

    public void showMostActiveUser() {
        logDao.showMostActiveUser();
    }

    public void actionsByType() {
        logDao.actionsByType();
    }
}
