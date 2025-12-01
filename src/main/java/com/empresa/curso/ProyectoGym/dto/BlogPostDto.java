package com.empresa.curso.ProyectoGym.dto;

import java.time.Instant;

public class BlogPostDto {

    private String title;
    private String link;
    private Instant publishedAt;

    public BlogPostDto() {}

    public BlogPostDto(String title, String link, Instant publishedAt) {
        this.title = title;
        this.link = link;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Instant getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
