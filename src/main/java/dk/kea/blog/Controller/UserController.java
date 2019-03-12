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
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/user/create")
    public String getUserForm(Model model) {
        model.addAttribute("users", service.getUsers());
        model.addAttribute("roles", service.getRoles());
        return "createUser";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute(name="user") User user, Model model) {
        if (service.createUser(user)) {

        }
        return "redirect:/user/create";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.deleteUser(id);
        return "redirect:/user/create";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {

        model.addAttribute("users", service.findUserById(id));
        model.addAttribute("roles", service.getRoles());
        return "editUser";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute (name="User") User user) {
        service.updateUser(user);
        return "redirect:/user/create";
    }

}
