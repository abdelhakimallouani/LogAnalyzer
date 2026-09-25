// package ma.youcode.lineperm.service;

// import java.lang.classfile.ClassFile.Option;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.StandardOpenOption;
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;
// import ma.youcode.lineperm.model.AccessLog;


// public class LogService {
//     private static final Path LOG_FILE = Path.of("data/access.log");
//     private final List<AccessLog> logs = new ArrayList<>();

//     public LogService() {
//         try {
//             if (!Files.exists(LOG_FILE)) {
//                 Files.createFile(LOG_FILE);
//             }
//         } catch (Exception e) {
//             System.out.println("Erreur craetion");
//         }

//         loadLog();
//     }

//     public void log(String utilisateur, String action, String fichier, boolean resultat) {

//         AccessLog accessLog = new AccessLog(utilisateur, action, fichier, resultat);

//         try {
//             Files.writeString(LOG_FILE, accessLog + System.lineSeparator(), StandardOpenOption.APPEND);
//         } catch (Exception e) {
//             System.out.println("Erreur creation");
//         }
//     }

//     public void loadLog() {
//         try {
//             List<String> lines = Files.readAllLines(LOG_FILE);

//             for (String line : lines) {

//                 String[] parts = line.split(";");

//                 AccessLog log = new AccessLog(parts[2], parts[3], parts[4], parts[5].equals("OK"));

//                 logs.add(log);

//             }
//         } catch (Exception e) {
//             System.out.println("Erreur de lecture");
//         }
//     }

//     public long countActions(){
//         return logs.stream().count();
//     }

//     public long countRefused(){
//         return  logs.stream().filter(log -> !log.getResultat()).count();
//     }

//     public long countDistinctUsers(){
//         return logs.stream().map(AccessLog::getUtilisateur).distinct().count();
//     }

//     public Map<String, Long> actionsByUser(){
//         return logs.stream().collect(Collectors.groupingBy(AccessLog::getUtilisateur,Collectors.counting()));
//     }

//     public Map<String, Long> top3Files(){
//         return logs.stream().collect(Collectors.groupingBy(AccessLog::getFichier,Collectors.counting()));
//     }

//     public List<AccessLog> refusedByUser(String username){
//         return logs.stream().filter(log -> log.getUtilisateur().equalsIgnoreCase(username) && !log.getResultat()).toList();
//     }

//     public Optional<String> showMostActiveUser(){
//         return logs.stream().collect(Collectors.groupingBy(AccessLog::getUtilisateur, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(entry -> entry.getKey()+ " (" + entry.getValue() + " actions)");
//     }

//     public Map<String, Long> actionsByType(){
//         return logs.stream().collect(Collectors.groupingBy(AccessLog::getAction,Collectors.counting()));
//     }
// }
