package dk.kea.blog.Repositories;

import dk.kea.blog.Models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class BlogRepository {

    private Connection con;
    private PreparedStatement preparedStatement;

    public BlogRepository() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host:3306/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
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

    public ResultSet getLatestBlogPosts() {
        String query = "SELECT * FROM blog INNER JOIN users ON blog.fk_users = users.id ORDER BY blog.id DESC LIMIT 3";
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

    public void updateBlog(Blog blog) {
        String query = "UPDATE blog SET title=?, text=? WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getText());
            preparedStatement.setInt(3, blog.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findBlogById(int id) {
        String query = "SELECT * FROM blog WHERE id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
