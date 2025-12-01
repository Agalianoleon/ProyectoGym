package com.empresa.curso.ProyectoGym.integration;

import java.time.Instant;
import java.util.Date;

import com.empresa.curso.ProyectoGym.dto.BlogPostDto;
import com.rometools.rome.feed.synd.SyndEntry;

public class FitGenerationEntryTransformer {
    public BlogPostDto toDto(SyndEntry entry) {

        // Obtener la fecha publicada del feed
        Instant published;
        
        Date publishedDate = entry.getPublishedDate();

        if (publishedDate != null) {
            published = publishedDate.toInstant();
        } else {
            published = Instant.now();
        }

        // 2. Construir el DTO explícitamente
        BlogPostDto dto = new BlogPostDto(
                entry.getTitle(),
                entry.getLink(),
                published
        );

        // 3. Devolverlo 
        return dto;
    }

}
