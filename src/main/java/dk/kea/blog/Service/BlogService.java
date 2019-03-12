package dk.kea.blog.Service;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Models.User;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    Database db = new Database();
    public List<Blog> getBlogPosts() {
        ResultSet rs = db.getBlogPosts();
        List<Blog> blogList = new ArrayList<>();
        try {
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getString("firstName"),
                        rs.getString("date")
                );
                blogList.add(blog);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return blogList;
    }

    public String createBlog(Blog blog) {
        if(blog.getTitle().length() < 1 ) {
            return "Length of title is too short. Minimum 1 character.";
        } else if (blog.getText().length() < 2) {
            return "Length of text is too short. Minimum 3 character.";
        } else {
            db.createPost(blog);
            return "The blog post was successfully created!";
        }

    }

    public boolean deleteBlog(int id) {
        db.delete("blog", id);
        return true;
    }

    public List<Blog> findBlogById(int id) {
        List<Blog> blogList = new ArrayList<>();
        ResultSet rs = db.findBlogById(id);
        try {
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setText(rs.getString("text"));
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }

    public String updateBlog(Blog blog) {

        if(blog.getTitle().length() < 1 ) {
            return "Length of title is too short. Minimum 1 character.";
        } else if (blog.getText().length() < 2) {
            return "Length of text is too short. Minimum 3 character.";
        } else {
            db.updateBlog(blog);
            return "The blog post was successfully updated!";
        }
    }

}
