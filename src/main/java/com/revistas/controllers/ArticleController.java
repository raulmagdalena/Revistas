package com.revistas.controllers;

import com.revistas.repositories.ArticleRepository;
import com.revistas.repositories.AuthorRepository;
import com.revistas.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private AuthorRepository authorRepository;
}
