package ma.youcode.lineperm.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ma.youcode.lineperm.model.LinFile;

public class FichierDao extends AbstractDao<LinFile> {
    @Override
    public LinFile save(LinFile fichier) {
        String sql = "INSERT INTO fichiers (name, owner_id, permission) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fichier.getName());
            statement.setLong(2, fichier.getOwnerId());
            statement.setString(3, fichier.getPermission().getValue());

            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();

            if (key.next()) {

                Long id = key.getLong(1);
                return new LinFile(id, fichier.getName(), fichier.getOwnerId(), fichier.getPermission());
            }
        } catch (SQLException e) {
            System.out.println("errur de creation fichier " + e.getMessage());
        }
        return null;
    }

}
