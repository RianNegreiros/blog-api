package com.github.riannegreiros.blogapi.service;

import com.github.riannegreiros.blogapi.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentByPostId(Long postId);
}
