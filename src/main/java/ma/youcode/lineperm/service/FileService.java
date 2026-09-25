package ma.youcode.lineperm.service;

import ma.youcode.lineperm.dao.FichierDao;
import ma.youcode.lineperm.dao.UserDao;
import ma.youcode.lineperm.enums.Permission;
import ma.youcode.lineperm.model.LinFile;
import ma.youcode.lineperm.model.User;

import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileService {

    private static final Path FILES_DIRECTORY = Path.of("data/files");
    // private final Scanner scanner;
    private final FichierDao fichierDao;
    private final UserDao userDao;

    public FileService() {

        fichierDao = new FichierDao();
        userDao = new UserDao();

        try {
            if (!Files.exists(FILES_DIRECTORY)) {
                Files.createDirectories(FILES_DIRECTORY);
            }
        } catch (Exception e) {
            System.out.println("Erreur de la creation ");
        }
    }

    public LinFile touch(String name, Long ownerId) {

        try {

            Path filePath = FILES_DIRECTORY.resolve(name);

            if (Files.exists(filePath)) {
                System.out.println("le fichie existe");

                return null;

            }

            // Optional<LinFile> existFile = fichierDao.findByName(name);

            // if (existFile.isPresent()) {
            // System.out.println("le file existe dans la bdd");
            // }

            Files.createFile(filePath);

            LinFile file = new LinFile(name, ownerId, Permission.Normale);

            LinFile saveFile = fichierDao.save(file);

            if (saveFile == null) {
                Files.deleteIfExists(filePath);
                System.out.println("error of save en db");
                return null;
            }

            System.out.println("file" + name + "creer");

            return saveFile;

        } catch (Exception e) {
            System.out.println("Erreur de la creation ");
            return null;
        }
    }

    public void ls() {
        try {
            List<LinFile> files = fichierDao.findAll();

            for (LinFile file : files) {
                Optional<User> user = userDao.findById(file.getOwnerId());
                String owner = " ";
                if (user.isPresent()) {
                    owner = user.get().getLogin();
                }

                System.out.println("rwd|"+ file.getPermission().getValue()+ " "+ owner + " "+ file.getName());
            }
        } catch (Exception e) {
            System.out.println("u dont have files" + e.getMessage());
        }
    }

    // public void cat(String fileName, String owner) {
    // try {

    // Path filePath = Path.of("data/files/" + fileName);

    // LinFile file = UserService.filesMap.get(fileName);

    // if (!Files.exists(filePath)) {
    // System.out.println("You don't have this file");
    // logService.log(owner, "LECTURE", fileName, false);
    // return;
    // }

    // if (file == null) {
    // System.out.println("File not found");
    // return;
    // }

    // Permission permission = file.getPermission();

    // if (!file.getOwner().equals(owner)) {

    // if (!permission.getValue().contains("r")) {
    // System.out.println("u dont have permission");
    // logService.log(owner, "LECTURE", fileName, false);
    // return;
    // }
    // }

    // String content = Files.readString(filePath);

    // if (content.isEmpty()) {
    // System.out.println("ur file is vide");
    // }

    // System.out.println(content);
    // logService.log(owner, "LECTURE", fileName, true);

    // } catch (Exception e) {
    // logService.log(owner, "LECTURE", fileName, false);
    // System.out.println("u dont have files" + e.getMessage());
    // }
    // }

    // public void nano(String fileName, String owner) {
    // Path filePath = Path.of("data/files/" + fileName);

    // LinFile file = UserService.filesMap.get(fileName);

    // if (!Files.exists(filePath)) {
    // System.out.println("You don't have this file");
    // logService.log(owner, "LECTURE", fileName, false);
    // return;
    // }

    // if (file == null) {
    // System.out.println("File not found");
    // return;
    // }

    // Permission permission = file.getPermission();

    // // System.out.println(permission.getValue());

    // if (!file.getOwner().equals(owner)) {

    // if (!permission.getValue().contains("rw")) {
    // System.out.println("u dont have permission");
    // logService.log(owner, "EDIT", fileName, false);
    // return;
    // }
    // }

    // try {

    // System.out.println("Saisis ton texte. Tape EOF seul sur une ligne pour
    // enregistrer.");

    // String oldContent = Files.readString(filePath);

    // if (oldContent.isEmpty()) {
    // System.out.println("file is vide ");
    // logService.log(owner, "EDIT", fileName, false);
    // } else {
    // System.out.println(oldContent);
    // logService.log(owner, "EDIT", fileName, true);
    // }

    // StringBuilder content = new StringBuilder();

    // int lineCount = 0;

    // while (true) {

    // String line = scanner.nextLine();

    // if (line.equals("EOF")) {
    // break;
    // }

    // content.append(line);
    // content.append(System.lineSeparator());

    // lineCount++;

    // }

    // Files.writeString(filePath, content, StandardOpenOption.APPEND);
    // logService.log(owner, "EDIT", fileName, true);

    // System.out.println("File : " + fileName + ", enregister (" + lineCount + "
    // ligne)");

    // } catch (Exception e) {
    // logService.log(owner, "EDIT", fileName, false);
    // System.out.println("Erreur lors de l'édition : " + e.getMessage());
    // }

    // }

    // public void chmod(String fileName, String permissionValue, String owner) {

    // try {

    // LinFile file = UserService.filesMap.get(fileName);

    // if (file == null) {
    // System.out.println("File not found");
    // logService.log(owner, "EDIT PERMISSION", fileName, false);
    // return;
    // }

    // if (!file.getOwner().equals(owner)) {
    // System.out.println("You are not the owner of this file");
    // logService.log(owner, "EDIT PERMISSION", fileName, false);
    // return;
    // }

    // Permission permission = null;

    // switch (permissionValue) {
    // case "r":
    // permission = Permission.R;
    // break;
    // case "-":
    // permission = Permission.Normale;
    // break;
    // case "rw":
    // permission = Permission.RW;
    // break;

    // default:
    // System.out.println("Invalid permission value. Use 'r', 'rw','-'");
    // logService.log(owner, "EDIT PERMISSION", fileName, false);
    // break;
    // }

    // file.setPermission(permission);
    // List<String> lines = Files.readAllLines(FILE_FILES);
    // for (int i = 0; i < lines.size(); i++) {
    // String[] parts = lines.get(i).split(" ", 3);
    // if (parts.length == 3 && parts[1].equals(owner) && parts[2].equals(fileName))
    // {
    // lines.set(i, "rwd|" + permission.getValue() + " " + owner + " " + fileName);
    // break;
    // }
    // }
    // Files.write(FILE_FILES, lines);
    // logService.log(owner, "EDIT PERMISSION", fileName, true);
    // System.out.println("Permission updated for file: " + fileName + " to " +
    // permission.getValue());

    // } catch (Exception e) {
    // logService.log(owner, "EDIT PERMISSION", fileName, false);
    // System.out.println("Error updating permissions in files.txt: " +
    // e.getMessage());
    // }
    // }

    // public void stats(){

    // }
}
