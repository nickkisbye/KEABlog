package dk.kea.blog.Controller;

import dk.kea.blog.Models.User;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfilController {

    @Autowired
    UserService userService;

    @PostMapping(value="/profil")
    public String changePassword(@ModelAttribute(name="User") User user, Model model, HttpSession session) {

        if(userService.changePassword(user)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        return "profil";
    }

}
