package dk.kea.blog.Controller;

import dk.kea.blog.Service.BlogService;
import dk.kea.blog.Service.FriendService;
import dk.kea.blog.Service.MessageService;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Autowired
    MessageService messageService;

    @GetMapping("/messages")
    public String index(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        model.addAttribute("friendlist", friendService.getFriends(userId));
        return "messages";
    }

    @GetMapping("/message/{id}")
    public String index(@PathVariable("id") int id ,Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        model.addAttribute("friendlist", friendService.getFriends(userId));
        model.addAttribute("messages", messageService.getMessages(id, userId));
        return "messages";
    }

}
