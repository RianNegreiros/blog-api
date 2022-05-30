package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.CreateUserRoleDTO;
import com.github.riannegreiros.blogapi.dto.SignUpDTO;
import com.github.riannegreiros.blogapi.entity.User;

public interface UserService {
    User createUser(SignUpDTO signUpDTO);
    User findUserByUsername(String username);

    User createUserRole(CreateUserRoleDTO createUserRoleDTO);
}
