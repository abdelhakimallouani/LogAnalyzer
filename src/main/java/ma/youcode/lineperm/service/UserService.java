package ma.youcode.lineperm.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ModuleElement.UsesDirective;

import ma.youcode.lineperm.model.User;
import ma.youcode.lineperm.model.LinFile;
import ma.youcode.lineperm.enums.Permission;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private static final String USERS_FILE = "data/users.txt";
    private static final String FILE_FILES = "data/files.txt";

    private final Map<String, User> users;
    public static Map<String, LinFile> filesMap;

    public UserService() {
        this.users = new HashMap<>();
        filesMap = new HashMap<>();
        loadUsers();
        loadFiles();
    }

    public void signUp(String login, String password) {

        login = login.trim();

        if (login.isEmpty()) {
            System.out.println("login invalide");
            return;
        }
        if (users.containsKey(login)) {
            System.out.println("login deja use");
            return;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("password invalide");
            return;
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(login, passwordHash);

        users.put(login, user);

        saveUsers();

        System.out.println("cmtp creer");
    }

    public User login(String login, String password) {

        if (login == null || login.isEmpty()) {
            System.out.println("login invalide");
            return null;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("password invalide");
            return null;
        }

        User user = users.get(login.trim());

        // System.out.println(user);

        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("login ou mot de passe incorrect");
            return null;
        }

        System.out.println("login reussi");

        return user;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {

            for (User user : users.values()) {
                writer.write(user.getLogin() + " : " + user.getPasswordHash());
                writer.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erreur de save users");
        }
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length != 2) {
                    continue;
                }

                String login = parts[0].trim();
                String password = parts[1].trim();

                User user = new User(login, password);

                users.put(login, user);

            }

        } catch (Exception e) {
            System.out.println("Erreur de charge users");
        }
    }

    private void loadFiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_FILES))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 3);
                if (parts.length != 3) {
                    continue;
                }

                String permessionValue = parts[0].trim();
                String owner = parts[1].trim();
                String fileName = parts[2].trim();

                // System.out.println(permessionValue + owner + fileName);

                Permission permission = Permission.fromValue(permessionValue);
                // System.out.println("khedama " + permission);

                // if (permission == null) {
                // System.out.println(
                // "Permission invalide : " + permessionValue);
                // continue;
                // }

                LinFile linfile = new LinFile(fileName, owner, permission);

                filesMap.put(fileName, linfile);

            }

            System.out.println(filesMap);
            // System.out.println("helloo hakim");

        } catch (Exception e) {
            System.out.println("Erreur de charge des fichiers");
        }
    }

}
