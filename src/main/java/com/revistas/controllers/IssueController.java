package com.revistas.controllers;


import com.revistas.entities.Issue;
import com.revistas.entities.Magazine;
import com.revistas.exceptions.IssueNotFoundException;
import com.revistas.exceptions.MagazineNotFoundException;
import com.revistas.repositories.IssueRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository repository;
    @Autowired
    private MagazineRepository magazineRepository;

    public IssueController(IssueRepository repository){
        this.repository = repository;
    }

    //Get all issues
    @GetMapping("/issues")
    public String getAllIssues(Model model){
        model.addAttribute("issues", repository.findAll());
        return "issues";
    }

    //Create a new issue
    @GetMapping("/new")
    public String newIssueForm(Model model){
        List<Magazine> listMagazines = magazineRepository.findAll();
        model.addAttribute("issue", new Issue());
        model.addAttribute("magazines", listMagazines);
        return " addissue";
    }

    //Save the issue
    @PostMapping("/saveissue")
    public String saveIssue(Issue issue){
        repository.save(issue);
        return "redirect:/issue/" + issue.getIdIssue();
    }

    //Get one issue by id
    @GetMapping(value = "/issue/{idIssue}")
    public String getIssueById(@PathVariable Long idIssue, Model model){
        try{
            model.addAttribute("issue",repository.findByIdIssue(idIssue));
            return "issue";
        } catch (EmptyResultDataAccessException e){
            throw new IssueNotFoundException(idIssue);
        }
    }
}
