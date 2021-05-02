package com.revistas.controllers;

import com.revistas.entities.Article;
import com.revistas.entities.Author;
import com.revistas.exceptions.IssueNotFoundException;
import com.revistas.repositories.ArticleRepository;
import com.revistas.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private ArticleRepository articleRepository;

    public AuthorController(AuthorRepository repository){
        this.repository = repository;
    }

    //Show author by id
    @GetMapping(value = "/author/{idAuthor}")
    public ModelAndView getAuthorById(@PathVariable Long idAuthor){
        try {
            ModelAndView modelAndView = new ModelAndView("authors/author");
            modelAndView.addObject("author", repository.findById(idAuthor));
            Author author = repository.findById(idAuthor);
            List<Article> articles = author.getArticles();
            modelAndView.addObject("articles", articles);
            return modelAndView;
        } catch (EmptyResultDataAccessException e){
            throw new IssueNotFoundException(idAuthor);
        }
    }
}
