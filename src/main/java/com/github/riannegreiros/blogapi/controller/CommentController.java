package com.github.riannegreiros.blogapi.controller;

import com.github.riannegreiros.blogapi.dto.CommentDTO;
import com.github.riannegreiros.blogapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD for Comments resources")
@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Get post comments")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentByPostId(postId);
    }

    @ApiOperation(value = "Get a single comment")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId) {
        CommentDTO commentDTO =  commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a comment")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a comment")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId, @Valid @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a comment")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
