package com.github.riannegreiros.blogapi.service.impl;

import com.github.riannegreiros.blogapi.dto.CreateUserRoleDTO;
import com.github.riannegreiros.blogapi.entity.Role;
import com.github.riannegreiros.blogapi.entity.User;
import com.github.riannegreiros.blogapi.exception.ResourceNotFoundException;
import com.github.riannegreiros.blogapi.repository.UserRepository;
import com.github.riannegreiros.blogapi.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUserRole(CreateUserRoleDTO createUserRoleDTO) {
        User userExists = userRepository.findById(createUserRoleDTO.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", String.valueOf(createUserRoleDTO.getIdUser())));

        List<Role> roles;

        roles = createUserRoleDTO.getIdsRoles().stream().map(Role::new).collect(Collectors.toList());

        userExists.setRoles(roles);

        userRepository.save(userExists);

        return userExists;
    }

    @Override
    public User createUser(User user) {
        Optional<User> existsUser = userRepository.findByUsername(user.getUsername());

        if (existsUser.isPresent()) {
            throw new Error("User already exists");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }
}
