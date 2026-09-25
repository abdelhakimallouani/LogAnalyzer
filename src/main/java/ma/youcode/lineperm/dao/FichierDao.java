package ma.youcode.lineperm.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.youcode.lineperm.model.LinFile;
import ma.youcode.lineperm.enums.*;

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

    public LinFile mapResultSetToFile(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String name = result.getString("name");
        Long owerId = result.getLong("owner_id");
        String permessionValue = result.getString("permission");

        Permission permission = Permission.fromValue(permessionValue);

        return new LinFile(id, name, owerId, permission);
    }

    public List<LinFile> findAll() {
        List<LinFile> files = new ArrayList<>();

        String sql = "SELECT f.id, f.name, f.owner_id, f.permission, u.login FROM fichiers f JOIN users u ON f.owner_id = u.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                LinFile file = mapResultSetToFile(result);
                files.add(file);
            }

        } catch (SQLException e) {
            System.out.println("errur of recuperation files " + e.getMessage());
            return null;
        }
        return files;
    }
}
