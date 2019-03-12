package dk.kea.blog.Repositories;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Models.User;

import java.sql.*;

public class Database {

    private Connection con;
    private PreparedStatement preparedStatement;

    public Database() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectUser(User user) {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id WHERE email = ? AND password = ? ";
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
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBlogPosts() {
        String query = "SELECT * FROM blog INNER JOIN users ON blog.fk_users = users.id ORDER BY blog.id DESC";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPost(Blog blog) {
        String query = "INSERT INTO blog (title, text, fk_users) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getText());
            preparedStatement.setInt(3, blog.getAid());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String deleteFrom, int id) {
        String query = "DELETE FROM "+deleteFrom+" WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        String query = "INSERT INTO users(firstName, lastName, city, email, age, password, fk_roles) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    public  ResultSet getUsers() {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id";
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
}
