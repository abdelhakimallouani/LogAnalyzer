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
}
