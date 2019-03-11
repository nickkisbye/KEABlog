package dk.kea.blog.Controller;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @GetMapping("/blog/create")
    public String getBlogForm() {
        return "blog";
    }

    @Autowired
    BlogService service;

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute (name="Blog") Blog blog) {
        if (service.createBlog(blog)) {
            //DO IT
        }
        return "admin";
    }

}
