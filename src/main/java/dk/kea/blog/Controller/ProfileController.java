package dk.kea.blog.Controller;

import dk.kea.blog.Models.User;
import dk.kea.blog.Service.FriendService;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    FriendService friendService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable ("id") int id, Model model) {
        if (friendService.getFriendsRequest(id).isEmpty()) {
            model.addAttribute("friend", false);
        } else {
            model.addAttribute("friend", true);
            model.addAttribute("friendrequests", friendService.getFriendsRequest(id));
        }
        model.addAttribute("friends", friendService.getFriends(id));
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
    public String userProfile(@PathVariable("id") int id, Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        model.addAttribute("users", userService.findUserById(id));
        model.addAttribute("friendlist", friendService.getFriends(id));
        model.addAttribute("friends", friendService.isFriends(id, userId));
        return "userProfile";
    }

    @PostMapping("/profile/addfriend")
    public String userProfile() {

        return "userProfile";
    }
}