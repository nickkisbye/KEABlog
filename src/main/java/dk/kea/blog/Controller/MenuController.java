package dk.kea.blog.Controller;

import dk.kea.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @Autowired
    BlogService service;

    @GetMapping("/posts")
    public String post(Model model) {
        model.addAttribute("blogs",service.getBlogPosts());
        return "posts";
    }

}
