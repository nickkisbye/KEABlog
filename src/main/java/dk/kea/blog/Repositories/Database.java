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
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
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
        String query = "SELECT * FROM blog INNER JOIN users ON blog.fk_users = users.id ORDER BY blog.id";
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
}
