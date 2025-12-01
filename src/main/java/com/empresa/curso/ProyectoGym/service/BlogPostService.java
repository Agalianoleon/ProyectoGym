package com.empresa.curso.ProyectoGym.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.empresa.curso.ProyectoGym.dto.BlogPostDto;
@Service
public class BlogPostService {
    
    private final List<BlogPostDto> latestPosts = new ArrayList<>();
    private static final int MAX_POSTS = 5;

    public synchronized void addPost(BlogPostDto post) {
        // Evitar duplicados por link
        latestPosts.removeIf(p -> p.getLink().equals(post.getLink()));
        latestPosts.add(post);

        // Ordenar por fecha desc
        latestPosts.sort(Comparator.comparing(BlogPostDto::getPublishedAt).reversed());

        // Limitar a los N últimos
        if (latestPosts.size() > MAX_POSTS) {
            latestPosts.subList(MAX_POSTS, latestPosts.size()).clear();
        }
    }

    public synchronized List<BlogPostDto> getLatestPosts() {
        return new ArrayList<>(latestPosts);
    }
}
