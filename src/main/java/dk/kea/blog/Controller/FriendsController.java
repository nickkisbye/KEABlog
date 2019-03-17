package dk.kea.blog.Controller;


import dk.kea.blog.Models.Friendship;
import dk.kea.blog.Service.FriendService;
import dk.kea.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class FriendsController {

    @Autowired
    FriendService service;

    @GetMapping("/acceptfriend/{id}")
    public String acceptFriend(@PathVariable("id") int id, HttpSession session) {
        service.acceptFriendRequest(id);
        return "redirect:/profile/" + session.getAttribute("id");
    }

    @GetMapping("/deletefriend/{id}")
    public String deleteFriendRequest(@PathVariable("id") int id, HttpSession session) {
        service.deleteFriendRequest(id);
        return "redirect:/profile/" + session.getAttribute("id");
    }

    @GetMapping("/addfriend/{id}")
    public String addFriend(@PathVariable("id") int id, HttpSession session) {
        Integer sid = (Integer) session.getAttribute("id");
        Friendship friendship = new Friendship(sid, id);
        service.addFriend(friendship);
        return "userProfile";
    }
}