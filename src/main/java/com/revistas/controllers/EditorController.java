package com.revistas.controllers;

import com.revistas.entities.Editor;
import com.revistas.exceptions.EditorNameAlreadyExists;
import com.revistas.exceptions.EditorNoName;
import com.revistas.exceptions.EditorNotFoundException;
import com.revistas.repositories.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/editors")
public class EditorController {

    @Autowired
    private EditorRepository repository;

    public EditorController(EditorRepository repository){
        this.repository = repository;
    }

    //Get all editors
    @GetMapping("/all")
    public String getAllEditors(Model model){
        //TODO check if an empty list is returned and throw an error
        model.addAttribute("editors", repository.findAll());
        return "/editors/editors";
    }

    //Show the form to add an editor
    @GetMapping("/new")
    public String showNewEditorForm(Editor editor){
        return "/editors/addeditor";
    }

    //Create a new editor
    @PostMapping("/addeditor")
    public String newEditor(@Valid Editor newEditor,BindingResult result, Model model){
        if (newEditor.getEditorName()==""){
            throw new EditorNoName();
        } else {
            String name = newEditor.getEditorName();
            if(nameExists(name)){
                throw new EditorNameAlreadyExists(newEditor.getEditorName());
            }
        }
        //TODO comprovar si s'ha guardat el resultat
        repository.save(newEditor);
        model.addAttribute("id", newEditor.getIdEditor());
        return "redirect:/editors/editor/" + newEditor.getIdEditor();
    }

    //Get an editor by id
    @GetMapping(value = "/editor/{id}")
    public String getEditorById(@PathVariable Long id, Model model){
        try{
            model.addAttribute("editor", repository.findById(id));
            return "/editors/editor";
        }   catch (EmptyResultDataAccessException e){
                throw new EditorNotFoundException(id);
        }
    }

    //Show the form to update an editor
    @GetMapping(value = "/editeditor/{id}")
    public String showUpdateEditorForm(@PathVariable("id") Long id, Model model){
        try {
               Editor editor = repository.findById(id);
               model.addAttribute("editor", editor);
               return "/editors/editeditor";
        } catch (EmptyResultDataAccessException e){
               throw new EditorNotFoundException(id);
        }
    }

    //Update the editor
    @PostMapping("/editeditor/{id}")
    public String updateEditor(@PathVariable("id") Long id, @Valid Editor editor, BindingResult result, Model model){
        if(result.hasErrors()){
            editor.setIdEditor(id);
            return "/editors/editeditor";
        }

        Editor editorUpdate = repository.getOne(id);
        editorUpdate.setEditorName(editor.getEditorName());
        repository.save(editorUpdate);
        return "redirect:/editors/editor/" + editorUpdate.getIdEditor();
    }

    //Delete editor
    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteEditor(@PathVariable Long id){
        try{
            repository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            throw new EditorNotFoundException(id);
        }
    }


    // utility functions
    private boolean nameExists(String name){
        if(repository.findByeditorName(name)!=null){
            return true;
        } else{
            return false;
        }
    }
}
