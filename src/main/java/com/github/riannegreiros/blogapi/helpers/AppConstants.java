package com.github.riannegreiros.blogapi.helpers;

public class AppConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";
    public static final String AUTH_TOKEN_TYPE = "Bearer ";
    public static final Integer TOKEN_EXPIRATION = 600_000;
    public static final Integer REFRESH_TOKEN_EXPIRATION = 86_400_000;
    public static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**"
    };
}
