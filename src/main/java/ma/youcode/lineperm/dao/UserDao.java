package ma.youcode.lineperm.dao;

import java.sql.*;

import ma.youcode.lineperm.model.User;

public class UserDao extends AbstractDao<User> {
    public UserDao() {
        System.out.println("User create ");
        System.out.println("Connection = " + connection);
    }

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

    // public findByUserName(String login){
    // public String requete = "SELECT "
    // }
}
