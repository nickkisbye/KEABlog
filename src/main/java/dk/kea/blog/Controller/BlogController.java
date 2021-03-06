package dk.kea.blog.Controller;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BlogController {

    @Autowired
    BlogService service;

    @GetMapping("/blog/create")
    public String getBlogForm(Model model) {
        model.addAttribute("blogs", service.getBlogPosts("all"));
        return "blog";
    }

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute (name="Blog") Blog blog, Model model) {
        model.addAttribute("message", service.createBlog(blog));
        model.addAttribute("blogs", service.getBlogPosts("all"));
        return "blog";
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id, HttpSession session) {

        service.deleteBlog(id, session);

        return"redirect:/blog/create";
    }

    @GetMapping("/blog/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("blogs", service.findBlogById(id));
        return "editBlog";
    }

    @PostMapping("/blog/update")
    public String updateUser(@ModelAttribute (name="Blog") Blog blog, Model model, int id) {
        model.addAttribute("message", service.updateBlog(blog));
        model.addAttribute("blogs", service.findBlogById(id));
        if (service.updateBlog(blog).equals("The blog post was successfully updated!")) {
            return getBlogForm(model);
        } else {
            return "editBlog";
        }
    }

}
