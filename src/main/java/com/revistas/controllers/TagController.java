package com.revistas.controllers;

import com.revistas.repositories.ArticleRepository;
import com.revistas.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagRepository repository;

    @Autowired
    private ArticleRepository articleRepository;
}

