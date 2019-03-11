package dk.kea.blog.Controller;

import dk.kea.blog.Models.User;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile() { return "profile"; }

    @Autowired
    UserService userService;

    @PostMapping(value="/profile")
    public String changePassword(@ModelAttribute(name="User") User user) {

        if(userService.changePassword(user)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        return "profile";
    }

}
