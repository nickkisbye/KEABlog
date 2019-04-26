package dk.kea.blog.Service;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Repositories.BlogRepository;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    Database db;
    public List<Blog> getBlogPosts(String amount) {
        ResultSet rs = null;
        if (amount.equals("all")) {
            rs = blogRepository.getBlogPosts();
        } else if (amount.equals("latest")) {
            rs = blogRepository.getLatestBlogPosts();
        }

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
            blogRepository.createPost(blog);
            return "The blog post was successfully created!";
        }

    }

    public void deleteBlog(int id, HttpSession session) {
        Integer requiredLevel = (Integer) session.getAttribute("level");
        Integer inSession = (Integer) session.getAttribute("id");
        if (inSession != null) {
            if (requiredLevel >= 90) {
                db.delete("blog", id);
            }
        }
    }

    public List<Blog> findBlogById(int id) {
        List<Blog> blogList = new ArrayList<>();
        ResultSet rs = blogRepository.findBlogById(id);
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
            blogRepository.updateBlog(blog);
            return "The blog post was successfully updated!";
        }
    }

}
