package ru.dmitry.crudapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dmitry.crudapplication.Entity.User;
import ru.dmitry.crudapplication.Service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "Views/user_list";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model, User user){
        return "Views/user_create";
    }

    @PostMapping("/users/new")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    public String findUserById(Model model, Long id){
        User user= userService.findById(id);
        model.addAttribute("user", user);
        return "Views/user";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";

    }

    @GetMapping("/users/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "Views/user_update";
    }

    @PostMapping("/users/update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
}
