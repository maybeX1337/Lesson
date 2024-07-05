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
@RequestMapping("/goods-api/good/{goodId:\\d+}")
@RequiredArgsConstructor
public class GoodsRestController {
    private final UserService userService;
    private final MessageSource messageSource;

    @ModelAttribute("good")
    public User user(@PathVariable("goodId") int goodId) {
        return userService.findUser(goodId).orElseThrow(() -> new NoSuchElementException("errors.user.not_found"));
    }

    @GetMapping()
    User findUserById(@ModelAttribute("good") User user) {
        return user;
    }

    @PatchMapping()
    ResponseEntity<?> updateUser(@PathVariable("goodId") int goodId,
                                 @Valid @RequestBody UpdateUserPayload updateUserPayload,
                                 BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        userService.updateUser(goodId, updateUserPayload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    ResponseEntity<?> deleteUser(@PathVariable("goodId") int userId) {
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