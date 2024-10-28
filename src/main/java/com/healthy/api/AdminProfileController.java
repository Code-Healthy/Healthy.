package com.healthy.api;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.model.entity.Profile;
import com.healthy.service.ProfileService;
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
@RequestMapping("admin/profiles")
public class AdminProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAll(){
        List<ProfileDTO> profile = profileService.getAll();
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProfileDTO>> paginate(
            @PageableDefault(size=5, sort= "gender")Pageable pageable){
        Page<ProfileDTO>  page = profileService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable("id") Integer id){
        ProfileDTO profile = profileService.findById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileFromDto){
        ProfileDTO profileCreated = profileService.create(profileFromDto);
        return new ResponseEntity<>(profileCreated, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> delete(@PathVariable Integer id){
        profileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}