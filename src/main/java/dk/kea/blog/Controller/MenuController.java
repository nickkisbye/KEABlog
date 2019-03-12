package dk.kea.blog.Controller;

import dk.kea.blog.Service.BlogService;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("blogs", blogService.getBlogPosts());
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @GetMapping("/posts")
    public String post(Model model) {
        model.addAttribute("blogs",blogService.getBlogPosts());
        return "posts";
    }

}
