package com.healthy.api;

import com.healthy.dto.AuthResponseDTO;
import com.healthy.dto.LoginDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.dto.UserRegistrationDTO;
import com.healthy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // EndPoint para Registrar usuario
    @PostMapping("/register/user")
    public ResponseEntity<UserRegistrationDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationDTO userRegistration = userService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }

}