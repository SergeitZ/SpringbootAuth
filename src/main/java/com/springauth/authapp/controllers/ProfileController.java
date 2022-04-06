package com.springauth.authapp.controllers;

import com.springauth.authapp.models.auth.User;
import com.springauth.authapp.models.profile.Profile;
import com.springauth.authapp.repositories.ProfileRepository;
import com.springauth.authapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public @ResponseBody
    List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @GetMapping("/profile")
    public @ResponseBody ResponseEntity<Profile> getProfile() {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }
        Profile currentProfile =  profileRepository.findByUser_id(currentUser.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(currentProfile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile newProfile) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        //TODO add check for existing developer profile.
        newProfile.setUser(currentUser);

        return new ResponseEntity<>(profileRepository.save(newProfile), HttpStatus.CREATED);
    }
}
