package com.healthy.api;
import com.healthy.dto.ProfileCreateUpdateDTO;
import com.healthy.dto.ProfileDetailsDTO;
import com.healthy.model.entity.Profile;
import com.healthy.service.AdminProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/profiles")


public class AdminProfileController {
    private final AdminProfileService adminProfileService;

    @GetMapping
    public ResponseEntity<List<ProfileDetailsDTO>> list(){
        List<ProfileDetailsDTO> profiles = adminProfileService.getAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProfileDetailsDTO>> paginate(
            @PageableDefault(size=5, sort= "gender")Pageable pageable){
        Page<ProfileDetailsDTO>  page = adminProfileService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfileDetailsDTO> getById(@PathVariable Integer id){
        ProfileDetailsDTO profile = adminProfileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProfileDetailsDTO> create(@Valid @RequestBody ProfileCreateUpdateDTO profileFromDto){
        ProfileDetailsDTO profileCreated = adminProfileService.create(profileFromDto);
        return new ResponseEntity<>(profileCreated, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDetailsDTO> update(@PathVariable Integer id,
                                                    @Valid @RequestBody ProfileCreateUpdateDTO profileFromDto){
        ProfileDetailsDTO updatedProfile = adminProfileService.update(id, profileFromDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> delete(@PathVariable Integer id){
        adminProfileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}