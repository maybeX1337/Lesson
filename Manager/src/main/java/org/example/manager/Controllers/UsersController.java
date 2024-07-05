package org.example.manager.Controllers;

import org.example.manager.Client.UsersRestClient;
import org.example.manager.Entity.Payloads.UserPayload;
import org.example.manager.Entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersRestClient usersRestClient;

    @GetMapping("list")
    String getUsers(Model model) {
        model.addAttribute("users", usersRestClient.findAllUsers());
        return "Users/list";
    }

    @GetMapping("new")
    String getNewUsers() {
        return "Users/new";
    }

    @PostMapping("new")
    String createUser(@Valid UserPayload userPayload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", userPayload);
            model.addAttribute("errors",
                    bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "Users/new";
        } else{
            User user = usersRestClient.createUser(userPayload.name(), userPayload.email(), userPayload.age());
            return "redirect:/users/%d".formatted(user.getId());
        }
    }

}
