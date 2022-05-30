package com.github.riannegreiros.blogapi.controller;

import com.github.riannegreiros.blogapi.dto.PostDTO;
import com.github.riannegreiros.blogapi.helpers.AppConstants;
import com.github.riannegreiros.blogapi.helpers.PostResponse;
import com.github.riannegreiros.blogapi.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD for Posts resources")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Get all posts from the Blog application")
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(page, size, sortBy, sortDir);
    }

    @ApiOperation(value = "Get a single post")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @ApiOperation(value = "Create a post")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a post")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {
        PostDTO postResponse = postService.updatePost(postDTO, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a post")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
