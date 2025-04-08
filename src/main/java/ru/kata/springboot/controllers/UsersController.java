package ru.kata.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.springboot.models.User;
import ru.kata.springboot.services.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //страница со всеми пользователями
    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    //страница с профилем 1 пользователя на которую можно перейти
    @GetMapping("/user")
    public String showOneUser(@RequestParam("id") int id,
                              Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "userProfile";
    }

    //мы должны в ХТМЛ форму передать пустого человека для формирования его полей
    //можно заменить на @ModelAttribute
    @GetMapping("/create")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    //Создаем нового Юзера, можно заменить на @ModelAttribute
    @PostMapping()
    public String createNewUser(@RequestParam("name") String name,
                                Model model) {
        User user = new User();
        user.setName(name);
        userService.saveUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    //страница для редактирования человека /edit?id=1
    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    //изменение пользователя без @ModelAttribute и без @PathVariable
    @PostMapping("/edit")
    public String update(@RequestParam("id") int id,
                         @RequestParam("name") String name) {
        User updatedUser = new User();
        updatedUser.setName(name);
        userService.updateUser(id, updatedUser);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
