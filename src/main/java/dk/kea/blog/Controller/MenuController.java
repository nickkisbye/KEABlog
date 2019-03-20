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
    public String index(Model model) {
        model.addAttribute("latestusers", userService.getUsers("latest"));
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("blogs", blogService.getBlogPosts("latest"));
        model.addAttribute("users", userService.getUsers("latest"));
        return "admin";
    }

    @GetMapping("/posts")
    public String post(Model model) {
        model.addAttribute("blogs", blogService.getBlogPosts("all"));
        return "posts";
    }

}
