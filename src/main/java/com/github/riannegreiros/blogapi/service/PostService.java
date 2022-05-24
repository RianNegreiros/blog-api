package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);
}