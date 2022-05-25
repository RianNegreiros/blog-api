package com.github.riannegreiros.blogapi.helpers;

import com.github.riannegreiros.blogapi.dto.PostDTO;

import java.util.List;

public class PostResponse {
    private List<PostDTO> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private Boolean last;

    public PostResponse() {
    }

    public PostResponse(List<PostDTO> content, int page, int size, Long totalElements, int totalPages, Boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<PostDTO> getContent() {
        return content;
    }

    public void setContent(List<PostDTO> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }
}
