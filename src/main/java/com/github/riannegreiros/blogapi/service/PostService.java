package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.PostDTO;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
}
