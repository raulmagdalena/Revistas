package com.revistas.controllers;

import com.revistas.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    public AuthorController(AuthorRepository repository){
        this.repository = repository;
    }
}
