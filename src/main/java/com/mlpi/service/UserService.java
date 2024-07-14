package com.mlpi.service;

import com.mlpi.dto.UserDto;
import com.mlpi.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
