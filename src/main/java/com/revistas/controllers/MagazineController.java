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
import java.util.List;

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
        return "magazines";
    }

    //Get one magazine by id
    @GetMapping(value = "/magazine/{idMagazine}")
    public String getMagazineById(@PathVariable Long idMagazine, Model model){
        try{
            model.addAttribute("magazine",repository.findByIdMagazine(idMagazine));
            return "magazine";
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
            return "editmagazine";
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
        return "addmagazine";
    }

    //Save the magazine
    @PostMapping("/savemagazine")
    public String saveMagazine(Magazine magazine){
        repository.save(magazine);
        return "redirect:/magazine/" + magazine.getIdMagazine();
    }

    //Update the magazine
    @PostMapping("/updatemagazine/{id}")
    public String updateMagazine(@PathVariable("id") Long id, @Valid Magazine magazine, BindingResult result, Model model){
        if(result.hasErrors()){
            magazine.setIdMagazine(id);
            return "editmagazine";
        }
        Magazine magazineUpdate = repository.findByIdMagazine(id);
        magazineUpdate.setMagazineName(magazine.getMagazineName());
        magazineUpdate.setMagazineIssn(magazine.getMagazineIssn());
        repository.save(magazineUpdate);
        return "redirect:/magazine/" + magazineUpdate.getIdMagazine();
    }

    //Get all the categories for the autocomplete
    @RequestMapping(value = "magazines/autocomplete")
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
