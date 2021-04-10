package com.revistas.controllers;

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
    public String getAuthorById(@PathVariable Long idAuthor, Model model){
        try {
            model.addAttribute("author", repository.findById(idAuthor));
            return "/authors/author";
        } catch (EmptyResultDataAccessException e){
            throw new IssueNotFoundException(idAuthor);
        }
    }
}
