package com.revistas.controllers;

import com.revistas.repositories.CategoryRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private MagazineRepository magazinesRepository;

    public CategoryController(CategoryRepository repository){this.repository = repository;}

}
