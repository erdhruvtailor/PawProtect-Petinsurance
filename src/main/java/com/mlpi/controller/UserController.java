package com.mlpi.controller;

import com.mlpi.dto.UserProfileDto;
import com.mlpi.model.User;
import com.mlpi.model.UserProfile;
import com.mlpi.service.UserProfileServiceImpl;
import com.mlpi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    UserProfileServiceImpl userProfileServiceImpl;

    @GetMapping("/")
    public String registrationForm() {
        return "user/user_dashboard";
    }

    @GetMapping("/userProfile")
    public String userProfileForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userServiceImpl.findUserByEmail(userDetails.getUsername());
        Long userId = user.getId();
        UserProfile userProfile = userProfileServiceImpl.getUserProfileById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("user", user);
        model.addAttribute("userProfile", userProfile);

        return "user/user_profile";
    }

    @GetMapping("/userProfile/update")
    public String userProfileForm(Model model, @RequestParam Long id) {

        try {
            UserProfile userProfile = userProfileServiceImpl.getUserProfileById(id);
            UserProfileDto userProfileDto = new UserProfileDto();

            if (userProfile == null) {

            } else {
                userProfileDto.setAddress(userProfile.getAddress());
                userProfileDto.setCity(userProfile.getCity());
                userProfileDto.setProvince(userProfile.getProvince());
                userProfileDto.setCountry(userProfile.getCountry());
                userProfileDto.setMobileNumber(userProfile.getMobileNumber());
                userProfileDto.setZipCode(userProfile.getZipCode());

            }

            System.out.println(userProfileDto);

            model.addAttribute("userProfileDto", userProfileDto);
            model.addAttribute("userProfile", userProfile);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/user/userProfile/";
        }

        return "user/user_profile_form";
    }

    @PostMapping("/userProfile/update")
    public String userProfileFormUpdate(Model model, @RequestParam Long id, @Valid @ModelAttribute UserProfileDto userProfileDto, BindingResult result) {

        try {
            UserProfile userProfile = userProfileServiceImpl.getUserProfileById(id);

            if (userProfile == null) {
                userProfile = new UserProfile();
            }

            userProfile.setAddress(userProfileDto.getAddress());
            userProfile.setCity(userProfileDto.getCity());
            userProfile.setProvince(userProfileDto.getProvince());
            userProfile.setCountry(userProfileDto.getCountry());
            userProfile.setMobileNumber(userProfileDto.getMobileNumber());
            userProfile.setZipCode(userProfileDto.getZipCode());
            userProfile.setUser(userServiceImpl.getSessionUser());

            userProfileServiceImpl.saveUserProfile(userProfile);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/user/userProfile/";
        }

        //return "user/user_profile_form";
        return "redirect:/user/userProfile";
    }
}
