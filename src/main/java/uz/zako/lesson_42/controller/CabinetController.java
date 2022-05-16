package uz.zako.lesson_42.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.zako.lesson_42.entity.entity.User;
import uz.zako.lesson_42.model.Result;
import uz.zako.lesson_42.service.CabinetService;
import uz.zako.lesson_42.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class CabinetController {

   @Autowired
    private CabinetService cabinetService;

   @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/all")
    public List<User> getAllUser() {
        System.out.println(cabinetService.getAllUser()+"=cabinetService.getAllUser()");
        return cabinetService.getAllUser();
    }

    @ResponseBody
    @GetMapping("/all/{id}")
    public User getAllUserId(@PathVariable Long id) {
        return cabinetService.getUserId(id);
    }

    @PostMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/api/auth/";
    }

    @PostMapping("/edit/{id}")
    public String deleteUser(@PathVariable Long id,@RequestBody User user) {
        cabinetService.UpdateUser(user);
        System.out.println(user.toString()+"user RequestBody");
        return "redirect:/api/auth/";
    }

    @ResponseBody
    @DeleteMapping("/user/{id}")
    public Result deleteUserAjax(@PathVariable Long id) {
        return userService.delete(id);
    }


}
