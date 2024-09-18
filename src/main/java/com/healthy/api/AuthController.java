package com.healthy.api;


import com.healthy.model.entity.User;
import com.healthy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")


public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
