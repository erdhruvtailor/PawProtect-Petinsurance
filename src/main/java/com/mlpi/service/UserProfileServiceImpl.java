package com.mlpi.service;

import com.mlpi.model.UserProfile;
import com.mlpi.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileById(Long id) {
        return userProfileRepository.getUserProfileByUser(id);
    }

    public UserProfile saveUserProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

}
