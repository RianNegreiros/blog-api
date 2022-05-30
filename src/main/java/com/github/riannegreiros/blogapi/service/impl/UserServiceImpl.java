package com.github.riannegreiros.blogapi.service.impl;

import com.github.riannegreiros.blogapi.dto.CreateUserRoleDTO;
import com.github.riannegreiros.blogapi.dto.SignUpDTO;
import com.github.riannegreiros.blogapi.entity.Role;
import com.github.riannegreiros.blogapi.entity.User;
import com.github.riannegreiros.blogapi.exception.ResourceNotFoundException;
import com.github.riannegreiros.blogapi.repository.UserRepository;
import com.github.riannegreiros.blogapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(SignUpDTO signUpDTO) {
        User user = mapToEntity(signUpDTO);
        Optional<User> existsUser = userRepository.findByUsername(user.getUsername());
        if (existsUser.isPresent()) {
            throw new Error("User already exists");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public User createUserRole(CreateUserRoleDTO createUserRoleDTO) {
        Optional<User> userExists = userRepository.findById(createUserRoleDTO.getIdUser());
        List<Role> roles = new ArrayList<>();

        if (userExists.isEmpty()) {
            throw new Error("User does not exists!");
        }

        roles = createUserRoleDTO.getIdsRoles().stream().map(Role::new).collect(Collectors.toList());

        User user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    private User mapToEntity(SignUpDTO signUpDTO) {
        return modelMapper.map(signUpDTO, User.class);
    }
}
