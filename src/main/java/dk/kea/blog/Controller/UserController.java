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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/userlist")
    public String getUserList(Model model) {
        List<User> userList = service.getUsers("all");
        model.addAttribute("users", userList);
        model.addAttribute("usercount", userList.size());
        return "users";
    }

    @GetMapping("/user/create")
    public String getUserForm(Model model) {
        model.addAttribute("users", service.getUsers("all"));
        model.addAttribute("roles", service.getRoles());
        return "createUser";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute(name="user") User user, Model model) {
        model.addAttribute("message", service.createUser(user));
        model.addAttribute("users", service.getUsers("all"));
        model.addAttribute("roles", service.getRoles());
        return "createUser";
    }

    @GetMapping("/user/register")
    public String getRegisterForm() {
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("message", service.createUser(user));
        return "register";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, HttpSession session) {
        service.deleteUser(id, session);
        return "redirect:/user/create";
    }

    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", service.findUserById(id));
        model.addAttribute("roles", service.getRoles());
        return "editUser";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute (name="User") User user, Model model, int id) {
        model.addAttribute("message", service.updateUser(user, id));
        model.addAttribute("users", service.findUserById(id));
        model.addAttribute("roles", service.getRoles());

        if (service.updateUser(user, id).equals("User have successfully been updated!")) {
            return getUserForm(model);
        } else {
            return "editUser";
        }
    }

}
