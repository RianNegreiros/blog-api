package com.github.riannegreiros.blogapi.dto;

import java.util.Set;

public class PostDTO {

    private Long id;
    private String title;
    private String description;
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
