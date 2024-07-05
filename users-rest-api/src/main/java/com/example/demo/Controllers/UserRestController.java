package com.example.demo.Controllers;

import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users-api/user/{userId:\\d+}")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final MessageSource messageSource;

    @ModelAttribute("user")
    public User user(@PathVariable("userId") int userId) {
        return userService.findUser(userId).orElseThrow(() -> new NoSuchElementException("errors.user.not_found"));
    }

    @GetMapping()
    User findUserById(@ModelAttribute("user") User user) {
        return user;
    }

    @PatchMapping()
    ResponseEntity<?> updateUser(@PathVariable("userId") int userId,
                                 @Valid @RequestBody UpdateUserPayload updateUserPayload,
                                 BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        userService.updateUser(userId, updateUserPayload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity <ProblemDetail> handleNoSuchElementException(NoSuchElementException e, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        messageSource.getMessage(e.getMessage(), new Object[0], locale)));
    }
}
