package com.revistas.controllers;

import com.revistas.repositories.CategoryRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private MagazineRepository magazinesRepository;

    public CategoryController(CategoryRepository repository){this.repository = repository;}

}
