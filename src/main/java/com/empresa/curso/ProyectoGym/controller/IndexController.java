package com.empresa.curso.ProyectoGym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.empresa.curso.ProyectoGym.service.BlogPostService;

@Controller
public class IndexController {

    private final BlogPostService blogPostService;

    public IndexController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", blogPostService.getLatestPosts());
        return "index";
    }
}
