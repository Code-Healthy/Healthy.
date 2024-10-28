package com.healthy.api;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.exception.ResourceConflictException;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.service.ProfileService;
import com.healthy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @PutMapping("/update")
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody UserProfileDTO userProfileDTO) {
        try {
            UserProfileDTO updatedProfile = userService.updateUserProfile(userProfileDTO);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (ResourceConflictException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //Obtener el perfil de un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer id) {
        UserProfileDTO userProfile = userService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileCreateDTO profileCreateDTO) {
        ProfileDTO createdProfile = profileService.create(profileCreateDTO);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }
}