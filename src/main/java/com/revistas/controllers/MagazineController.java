package com.revistas.controllers;

import com.revistas.entities.Category;
import com.revistas.entities.Editor;
import com.revistas.entities.Magazine;
import com.revistas.exceptions.MagazineNotFoundException;
import com.revistas.repositories.CategoryRepository;
import com.revistas.repositories.EditorRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    @Autowired
    private MagazineRepository repository;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public MagazineController(MagazineRepository repository){
        this.repository = repository;
    }

    //Get all Magazines
    @GetMapping("/all")
    public String getAllMagazines(Model model){
        //TODO check if an empty list is returned and throw an error
        model.addAttribute("magazines", repository.findAll());
        return "/magazines/magazines";
    }

    //Get one magazine by id
    @GetMapping(value = "/magazine/{idMagazine}")
    public String getMagazineById(@PathVariable Long idMagazine, Model model){
        try{
            model.addAttribute("magazine",repository.findByIdMagazine(idMagazine));
            return "/magazines/magazine";
        } catch (EmptyResultDataAccessException e){
            throw new MagazineNotFoundException(idMagazine);
        }
    }

    //Edit a magazine
    @GetMapping("/editmagazine/{id}")
    public String editmagazine(@PathVariable("id") Long id, Model model){
        try{
            model.addAttribute("magazine", repository.findByIdMagazine(id));
            model.addAttribute("editors", editorRepository.findAll());
            return "/magazines/editmagazine";
        } catch (EmptyResultDataAccessException e){
            throw new MagazineNotFoundException(id);
        }
    }


    //Create a new magazine
    @GetMapping(value = {"/new", "/new/{idEditor}"})
    public String newMagazineForm(@PathVariable(required = false) Long idEditor, Model model){
        if(idEditor == null) {
            List<Editor> listEditors = editorRepository.findAll();
            model.addAttribute("magazine", new Magazine());
            model.addAttribute("editors", listEditors);
        }else{
            Editor editor = editorRepository.findById(idEditor);
            model.addAttribute("magazine", new Magazine());
            model.addAttribute("editor", editor);
        }
        return "/magazines/addmagazine";
    }

    //Save the magazine
    @PostMapping("/savemagazine")
    public String saveMagazine(@ModelAttribute Magazine magazine, BindingResult result, @RequestParam("categories") String strCategories, Model model){
        if(result.hasErrors()) {
            Set<Category> categoriesSet = new HashSet<>();
            for (String category : strCategories.split(",")) {
                if (category.equals("") || category == null) {
                    //don't do anything
                } else {
                    String trimCat;
                    trimCat = category.trim();
                    Category cat = categoryRepository.findByCategoryName(trimCat);
                    if (cat == null) {
                        cat = new Category();
                        cat.setCategoryName(trimCat);
                        categoryRepository.save(cat);
                    }
                    categoriesSet.add(cat);
                }
            }
            magazine.setCategories(categoriesSet);
            repository.save(magazine);
            return "redirect:/magazines/magazine/" + magazine.getIdMagazine();
        }
        return "redirect:/magazines/new";
    }

    //Update the magazine
    @PostMapping("/updatemagazine/{idMagazine}")
    public String updateMagazine(@PathVariable(value = "idMagazine", required = true) Long idMagazine, @Valid Magazine magazine, BindingResult result){
        if(result.hasErrors()){
            magazine.setIdMagazine(idMagazine);
            return "/magazines/editmagazine";
        }
        Magazine magazineUpdate = repository.findByIdMagazine(idMagazine);
        magazineUpdate.setMagazineName(magazine.getMagazineName());
        magazineUpdate.setMagazineIssn(magazine.getMagazineIssn());
        repository.save(magazineUpdate);
        return "redirect:/magazines/magazine/" + magazineUpdate.getIdMagazine();
    }

    //Get all the categories for the autocomplete
    @RequestMapping(value = "/autocomplete")
    @ResponseBody
    public List<String> autoComplete(@RequestParam(value = "term", required = false, defaultValue = "") String term){
        List<String> categoriesList = categoryRepository.getCategories(term);
        return categoriesList;
    }

    //Utility functions
    private boolean nameExists(String magazineName){
        if(repository.findByMagazineName(magazineName)!=null){
            return true;
        } else {
            return false;
        }
    }
}
