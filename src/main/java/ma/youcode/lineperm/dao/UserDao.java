package ma.youcode.lineperm.dao;

import java.sql.*;
import java.util.Optional;

import ma.youcode.lineperm.model.User;

public class UserDao extends AbstractDao<User> {

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (login,password) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordHash());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                Long id = keys.getLong(1);
                return new User(id, user.getLogin(), user.getPasswordHash());
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Errrour of add : " + e.getMessage());
            return null;
        }
    }

    public Optional<User> findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                User user = new User(result.getLong("id"), result.getString("login"), result.getString("password"));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            System.out.println("Error of find by name " + e.getMessage());
        }
        return Optional.empty();
    }
}
