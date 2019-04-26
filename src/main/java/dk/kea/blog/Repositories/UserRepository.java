package dk.kea.blog.Repositories;

import dk.kea.blog.Models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {

    private Connection con;
    private PreparedStatement preparedStatement;

    public UserRepository() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host:3306/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectUser(User user) {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id WHERE email = ? AND password = md5(?) ";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newPasswordDB(User user){
        String sql = "UPDATE users SET password = md5(?) WHERE email = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        String query = "INSERT INTO users(firstName, lastName, city, email, age, password, fk_roles) VALUES (?, ?, ?, ?, ?, md5(?), ?)";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getRid());
            preparedStatement.execute();
            preparedStatement.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findUserById(int id) {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id WHERE users.id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET firstName=?, lastName=?, city=?, email=?, age=?, password=md5(?), fk_roles=? WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getRid());
            preparedStatement.setInt(8,user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  ResultSet getUsers() {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id ORDER BY users.id DESC";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ResultSet getLatestUser() {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id ORDER BY users.id DESC LIMIT 3";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ResultSet getRoles() {
        String query = "SELECT * FROM roles";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet userAlreadyexists(String email) {
        String query = "SELECT email, id FROM users WHERE email=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet userAlreadyexistsUpdate(String email, int id) {
        String query = "SELECT email FROM users WHERE email=? AND id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
