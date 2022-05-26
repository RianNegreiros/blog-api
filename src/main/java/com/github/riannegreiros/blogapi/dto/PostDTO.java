package com.github.riannegreiros.blogapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class PostDTO {

    private Long id;
    @NotBlank
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
    private Set<CommentDTO> commentDTOSet;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String description, String content, Set<CommentDTO> commentDTOSet) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.commentDTOSet = commentDTOSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<CommentDTO> getCommentDTOSet() {
        return commentDTOSet;
    }

    public void setCommentDTOSet(Set<CommentDTO> commentDTOSet) {
        this.commentDTOSet = commentDTOSet;
    }
}
