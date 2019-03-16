package dk.kea.blog.Controller;

import dk.kea.blog.Models.User;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable ("id") int id, Model model) {
        model.addAttribute("friendrequests", userService.getFriendsRequest(id));
        model.addAttribute("friends", userService.getFriends(id));
        return "profile";
    }

    @Autowired
    UserService userService;

    @PostMapping(value="/profile")
    public String changePassword(@ModelAttribute(name="User") User user, Model model) {
        model.addAttribute("message", userService.changePassword(user));

        return "profile";
    }

    @GetMapping("/userprofile/{id}")
    public String userProfile(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.findUserById(id));
        return "userProfile";
    }

    @PostMapping("/profile/addfriend")
    public String userProfile() {

        return "userProfile";
    }
}