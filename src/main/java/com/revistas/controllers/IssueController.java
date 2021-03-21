package com.revistas.controllers;


import com.revistas.entities.Issue;
import com.revistas.entities.Magazine;
import com.revistas.entities.Tag;
import com.revistas.exceptions.IssueNotFoundException;
import com.revistas.exceptions.MagazineNoIdException;
import com.revistas.exceptions.MagazineNotFoundException;
import com.revistas.repositories.IssueRepository;
import com.revistas.repositories.MagazineRepository;
import com.revistas.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository repository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private TagRepository tagRepository;

    public IssueController(IssueRepository repository){
        this.repository = repository;
    }

    //Get all issues
    @GetMapping("/all")
    public String getAllIssues(Model model){
        model.addAttribute("issues", repository.findAll());
        return "/issues/issues";
    }

    //Create a new issue
    @GetMapping("/new/{idMagazine}")
    public String newIssueForm(@PathVariable(required = true) Long idMagazine, Model model){
        if(idMagazine != null) {
            try {
                Magazine magazine = magazineRepository.findByIdMagazine(idMagazine);
                model.addAttribute("issue", new Issue());
                model.addAttribute("magazine", magazine);
                return "/issues/addissue";
            } catch (EmptyResultDataAccessException e){
                throw new MagazineNotFoundException(idMagazine);
            }
        }else{
            throw new MagazineNoIdException();
        }
    }

    //Save the issue
    @PostMapping("/saveissue")
    public String saveIssue(@ModelAttribute Issue issue, BindingResult result, @RequestParam("tags") String strTags ){
        if(result.hasErrors()){
            for (String tag : strTags.split(",")){
                if(tag.equals("") || tag == null){
                    // don't do anything
                } else {
                    String trimTag;
                    trimTag = tag.trim();
                    Tag newtag = tagRepository.findByTagName(trimTag);
                    if(newtag == null){
                        newtag = new Tag();
                        newtag.setTagName(trimTag);
                        tagRepository.save(newtag);
                    }
                    issue.addTag(newtag);
                }
            }
            repository.save(issue);
            return "redirect:/issues/issue/" + issue.getIdIssue();
        }
        return "redirect:/issues/new";
    }

    //Get one issue by id
    @GetMapping(value = "/issue/{idIssue}")
    public String getIssueById(@PathVariable Long idIssue, Model model){
        try{
            model.addAttribute("issue",repository.findByIdIssue(idIssue));
            return "/issues/issue";
        } catch (EmptyResultDataAccessException e){
            throw new IssueNotFoundException(idIssue);
        }
    }

    //Get all the tags for the autocomplete
    @RequestMapping(value = "/autocomplete")
    @ResponseBody
    public List<String> autoComplete(@RequestParam(value = "term", required = false, defaultValue = "") String term){
        List<String> tagsList =tagRepository.getTags(term);
        return tagsList;
    }
}
