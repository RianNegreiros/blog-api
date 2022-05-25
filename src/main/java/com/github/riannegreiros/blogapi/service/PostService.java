package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.PostDTO;
import com.github.riannegreiros.blogapi.helpers.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(Integer page, Integer size, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePostById(Long id);
}
