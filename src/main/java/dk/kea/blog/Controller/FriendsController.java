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

@Controller
public class FriendsController {

    @Autowired
    FriendService service;

    @GetMapping("/acceptfriend/{id}/{sid}")
    public String acceptFriend(@PathVariable("id") int id, @PathVariable("sid") int sid) {
        service.acceptFriendRequest(id);
        return "redirect:/profile/{sid}";
    }

    @GetMapping("/deletefriend/{id}/{sid}")
    public String deleteFriendRequest(@PathVariable("id") int id, @PathVariable("sid") int sid) {
        service.deleteFriendRequest(id);
        return "redirect:/profile/{sid}";
    }

    @GetMapping("/addfriend/{id}/{sid}")
    public String addFriend(@PathVariable("id") int id, @PathVariable("sid") int sid) {
        Friendship friendship = new Friendship(sid, id);
        service.addFriend(friendship);
        return "userProfile";
    }
}