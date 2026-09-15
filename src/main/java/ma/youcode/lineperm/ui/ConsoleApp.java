package ma.youcode.lineperm.ui;

import ma.youcode.lineperm.model.LinFile;
import ma.youcode.lineperm.model.User;
import ma.youcode.lineperm.service.UserService;

import java.util.Scanner;

import ma.youcode.lineperm.service.FileService;
import ma.youcode.lineperm.service.FileService;

public class ConsoleApp {

    private final Scanner scanner;
    private final UserService userService;
    private final FileService fileService;

    private User currentUser;

    public ConsoleApp() {
        scanner = new Scanner(System.in);
        userService = new UserService();
        fileService = new FileService(scanner);
        currentUser = null;
    }

    public void start() {

        System.out.println("============================================");
        System.out.println("        Bienvenue dans LinePermission");
        System.out.println("============================================");
        System.out.println("Commandes : signup | login | help | exit" + "\n");

        while (true) {

            if (currentUser == null) {
                System.out.print("linperm> ");
            } else {
                System.out.print(currentUser.getLogin() + "@linperm> ");
            }

            String line = scanner.nextLine().trim();

            String[] mots = line.split("\\s+");

            String command = mots[0].toLowerCase();

            switch (command) {

                case "signup":
                    signup();
                    break;

                case "login":
                    login();
                    break;

                case "logout":
                    logout();
                    break;

                case "help":
                    help();
                    break;

                case "touch":
                    touch(mots);
                    break;
                case "ls":
                    ls();
                    break;
                case "cat":
                    cat(mots[1], currentUser.getLogin());
                    break;
                case "nano":
                    nano(mots);
                    break;
                case "chmod":
                    chmod(mots);
                    break;
                case "exit":
                    System.out.println("Au revoir.");
                    return;

                default:
                    System.out.println("Commande inconnue. Tape 'help'");
            }
        }
    }

    private void signup() {

        System.out.print("Login : ");
        String login = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        userService.signUp(login, password);
    }

    private void login() {

        System.out.print("Login : ");
        String login = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        try {
            User user = userService.login(login, password);
            currentUser = user;

        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }

        if (currentUser != null) {
            System.out.println("Bienvenue " + currentUser.getLogin());
            System.out.println("-------------------------------------");

        }
    }

    private void logout() {
        System.out.println("Deconnecte");
        currentUser = null;
    }

    private void help() {

        if (currentUser == null) {
            System.out.println("Commandes : signup | login | help | exit");
        } else {
            System.out.println("Commandes : logout | help | exit | touch");
        }
    }

    private void touch(String[] mots) {
        if (currentUser == null) {
            System.out.println("u are conneted");
            return;
        }

        if (mots.length != 2) {
            System.out.println("Usage : touch <nom_fichier>");
            return;
        }

        String fileName = mots[1];

        LinFile file = fileService.touch(fileName, currentUser.getLogin());

        if (file != null) {
            System.out.println("Fichier created : " + fileName);
        }
    }

    private void ls() {
        fileService.ls();
    }

    private void cat(String fileName, String owner) {
        fileService.cat(fileName, owner);
    }

    private void nano(String[] mots) {

        if (mots.length != 2) {
            System.out.println("use : nano <file>");
        }

        String fileName = mots[1];

        fileService.nano(fileName, currentUser.getLogin());

    }

    private void chmod(String[] mots) {
        if (mots.length != 3) {
            System.out.println("Usage : chmod <permission> <file>");
            return;
        }

        String permission = mots[1];
        String fileName = mots[2];

        fileService.chmod(fileName, permission, currentUser.getLogin());
    }
}
