package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    User signUpUser(User user);

    User saveUser(User user);
    User updateUser(User user, Authentication authentication);

    void deleteUserById(Long id);
    void deleteUserByEmail(String email);
}
