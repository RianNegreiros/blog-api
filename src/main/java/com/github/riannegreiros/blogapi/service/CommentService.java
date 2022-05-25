package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);
}
