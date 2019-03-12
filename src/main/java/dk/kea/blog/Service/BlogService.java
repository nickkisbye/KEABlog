package dk.kea.blog.Service;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean createBlog(Blog blog) {
        db.createPost(blog);
        return true;
    }

    public boolean deleteBlog(int id) {
        db.delete("blog", id);
        return true;
    }

}
