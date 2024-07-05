package org.example.manager.Controllers;

import org.example.manager.Client.UsersRestClient;
import org.example.manager.Entity.Payloads.UpdateUserPayload;
import org.example.manager.Entity.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users/{userId:\\d+}")
@RequiredArgsConstructor
public class UserController {
    private final UsersRestClient usersRestClient;
    private final MessageSource messageSource;

    @ModelAttribute("user")
    public User user(@PathVariable("userId") int userId) {
        return usersRestClient.findUserById(userId).orElseThrow(() -> new NoSuchElementException("errors.user.not_found"));
    }

    @GetMapping()
    String findUser(){
        return "Users/user";
    }

    @GetMapping("update")
    String updateUser(){
        return "Users/update";
    }

    @PostMapping("update")
    String updateUser(@ModelAttribute("user") User user, @Valid UpdateUserPayload updateUserPayload, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", updateUserPayload);
            model.addAttribute("errors",
                    bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());
            return "Users/update";
        }
        usersRestClient.updateUser(user.getId(), updateUserPayload);
        return "redirect:/users/%d".formatted(user.getId());
    }

    @GetMapping("delete")
    String deleteUser(@ModelAttribute("user") User user){
        usersRestClient.deleteUser(user.getId());
        return "redirect:/users/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e, Model model, HttpServletResponse response, Locale locale){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", messageSource.getMessage(e.getMessage(), new Object[0], e.getMessage(), locale));
        return "Errors/404";
    }
}
