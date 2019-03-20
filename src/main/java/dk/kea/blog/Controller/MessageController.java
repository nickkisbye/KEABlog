package dk.kea.blog.Controller;

import dk.kea.blog.Models.Message;
import dk.kea.blog.Service.BlogService;
import dk.kea.blog.Service.FriendService;
import dk.kea.blog.Service.MessageService;
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

    @GetMapping("/messages/{id}")
    public String index(@PathVariable("id") int id ,Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("id");
        model.addAttribute("friendlist", friendService.getFriends(userId));
        model.addAttribute("messages", messageService.getMessages(id, userId));
        model.addAttribute("findFriend", messageService.getFriend(id));
        return "messages";
    }

    @PostMapping("/messages")
    public String createMsg(@ModelAttribute(name="Message") Message message, Model model,  HttpSession session) {
        Integer sessionId = (Integer) session.getAttribute("id");
        messageService.insertMessage(message, sessionId, message.getReceiverUser());
        return "redirect:/messages/" + message.getReceiverUser();
    }
}
