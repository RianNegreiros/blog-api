package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.CreateUserRoleDTO;
import com.github.riannegreiros.blogapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUserRole(CreateUserRoleDTO createUserRoleDTO);
    User createUser(User user);
}
