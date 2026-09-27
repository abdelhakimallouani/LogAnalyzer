package ma.youcode.lineperm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ma.youcode.lineperm.model.AccessLog;

public class LogDao extends AbstractDao<AccessLog> {
    @Override
    public AccessLog save(AccessLog log) {
        String sql = "INSERT INTO logs (user_id, file_id, action,resultat, date, heure) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, log.getUserId());
            statement.setLong(2, log.getFileId());
            statement.setString(3, log.getAction());
            statement.setString(4, log.getResultat());
            statement.setObject(5, log.getDate());
            statement.setString(6, log.getHeure());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                Long id = result.getLong(1);
                return new AccessLog(id, log.getUserId(), log.getFileId(), log.getAction(), log.getResultat());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countTotalActions() {
        String sql = "SELECT COUNT(*) FROM logs";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultat = statement.executeQuery();
            if (resultat.next()) {
                return resultat.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("errur : " + e.getMessage());
        }
        return 0;
    }

    public int countRefusedActions() {

        String sql = "SELECT COUNT(*) FROM logs WHERE resultat = 'REFUSED'";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return 0;
    }

    public int countDistinctUsers() {

        String sql = " SELECT COUNT(DISTINCT user_id) FROM logs";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println( "Erreur : " + e.getMessage());
        }

        return 0;
    }

    public void actionsByUser(){
        String sql = "SELECT u.login, COUNT(*) as action_count FROM logs l JOIN users u ON l.user_id = u.id GROUP BY u.id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String userLogin = result.getString("login");
                int actionCount = result.getInt("action_count");
                System.out.println(userLogin + " : " + actionCount + " actions");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void top3Files() {
        String sql = "SELECT f.name, COUNT(*) as action_count FROM logs l JOIN fichiers f ON l.file_id = f.id GROUP BY f.id ORDER BY action_count DESC LIMIT 3";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String fileName = result.getString("name");
                int actionCount = result.getInt("action_count");
                System.out.println(fileName + " : " + actionCount + " actions");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    public void refusedByUser(String userLogin) {
        String sql = "SELECT f.name, COUNT(*) as refused_count FROM logs l JOIN fichiers f ON l.file_id = f.id JOIN users u ON l.user_id = u.id WHERE l.resultat = 'REFUSED' AND u.login = ? GROUP BY f.id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userLogin);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String fileName = result.getString("name");
                int refusedCount = result.getInt("refused_count");
                System.out.println(fileName + " : " + refusedCount + " refusals");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public  void showMostActiveUser(){
        String sql = "SELECT u.login, COUNT(*) as action_count FROM logs l JOIN users u ON l.user_id = u.id GROUP BY u.id ORDER BY action_count DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String userLogin = result.getString("login");
                int actionCount = result.getInt("action_count");
                System.out.println("active user: " + userLogin + ", " + actionCount + " actions");
            } else {
                System.out.println("No actions found");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void actionsByType() {
        String sql = "SELECT action, COUNT(*) as action_count FROM logs GROUP BY action";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String actionType = result.getString("action");
                int actionCount = result.getInt("action_count");
                System.out.println(actionType + " : " + actionCount + " actions");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
