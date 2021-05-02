package com.revistas.controllers;

import com.revistas.entities.Category;
import com.revistas.repositories.CategoryRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private MagazineRepository magazinesRepository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    public void addCategory(Category category) {
        repository.save(category);
    }

    //TODO, al moment de guardar, mirar si la categoria ja existeix. Ara mateix la taula 'category_magazine' no si guarda res}

    @GetMapping("/category/{idCategory}")
    public ModelAndView getCategoryById(@PathVariable() Long idCategory){
        ModelAndView modelAndView = new ModelAndView("/categories/category");
        modelAndView.addObject("category", repository.findByIdCategory(idCategory));
        return modelAndView;
    }
}