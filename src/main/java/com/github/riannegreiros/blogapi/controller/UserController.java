package com.github.riannegreiros.blogapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.riannegreiros.blogapi.dto.CreateUserRoleDTO;
import com.github.riannegreiros.blogapi.dto.SignUpDTO;
import com.github.riannegreiros.blogapi.entity.Role;
import com.github.riannegreiros.blogapi.entity.User;
import com.github.riannegreiros.blogapi.exception.BlogAPIException;
import com.github.riannegreiros.blogapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.riannegreiros.blogapi.helpers.AppConstants.AUTH_TOKEN_TYPE;
import static com.github.riannegreiros.blogapi.helpers.AppConstants.TOKEN_EXPIRATION;
import static com.github.riannegreiros.blogapi.jwt.JWTConstants.JWT_SECRET;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody SignUpDTO signUpDTO) {
        return userService.createUser(signUpDTO);
    }

    @PostMapping("/role")
    public User createUserRole(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        return userService.createUserRole(createUserRoleDTO);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader != null && authorizationHeader.startsWith(AUTH_TOKEN_TYPE)) {
                try {
                    String refresh_token = authorizationHeader.substring(AUTH_TOKEN_TYPE.length());
                    Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refresh_token);
                    String username = decodedJWT.getSubject();
                    User user = userService.findUserByUsername(username);
                    String access_token = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                            .sign(Algorithm.HMAC512(JWT_SECRET));
                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", access_token);
                    tokens.put("refresh_token", refresh_token);
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                } catch (Exception e) {
                    response.setHeader("ERROR", e.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Refresh token is missing");
            }
    }
}
