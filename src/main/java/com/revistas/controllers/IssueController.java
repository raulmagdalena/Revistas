package com.revistas.controllers;


import com.revistas.entities.*;
import com.revistas.exceptions.IssueNotFoundException;
import com.revistas.exceptions.MagazineNoIdException;
import com.revistas.exceptions.MagazineNotFoundException;
import com.revistas.repositories.ArticleRepository;
import com.revistas.repositories.IssueRepository;
import com.revistas.repositories.MagazineRepository;
import com.revistas.repositories.TagRepository;
import com.revistas.services.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository repository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CoverService coverService;
    @Autowired
    private ArticleRepository articleRepository;

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
    @GetMapping(value = {"/new", "/new/{idMagazine}"})
    public String newIssueForm(@PathVariable(required = false) Long idMagazine, Model model){
        if(idMagazine != null) {
            try {
                Magazine magazine = magazineRepository.findByIdMagazine(idMagazine);
                model.addAttribute("issue", new Issue());
                model.addAttribute("magazine", magazine);
            } catch (EmptyResultDataAccessException e){
                throw new MagazineNotFoundException(idMagazine);
            }
        }else{
            List<Magazine> listMagazines = magazineRepository.findAll();
            model.addAttribute("issue", new Issue());
            model.addAttribute("editors", listMagazines);
        }
        return "/issues/addissue";
    }

    //Save the issue
    @PostMapping("/saveissue")
    public String saveIssue(@ModelAttribute Issue issue, BindingResult result, @RequestParam("tags") String strTags, RedirectAttributes redirectAttributes) {
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
            try {
                repository.save(issue);
                redirectAttributes.addFlashAttribute("message", "success");
                return "redirect:/issues/issue/" + issue.getIdIssue();
            }catch (Exception e){
                if(e instanceof SQLIntegrityConstraintViolationException){

                }
                return "redirect:/issues/new";
            }
        }
        return "redirect:/issues/new";
    }

    //Get one issue by id
    @GetMapping(value = "/issue/{idIssue}")
    public String getIssueById(@PathVariable Long idIssue, Model model, HttpServletRequest request){
        try{
            Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
            if(inputFlashMap != null){
                String success = (String) inputFlashMap.get("message");
            }
            Issue issue = repository.findByIdIssue(idIssue);
            model.addAttribute("issue",repository.findByIdIssue(idIssue));
            model.addAttribute("articles", articleRepository.findByIdIssueOrderByArticleOrderASC(issue.getIdIssue()));
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

    //Add dinamically an article
    @RequestMapping(value = "addarticle", params = {"addArticle"})
    public String addArticle(Issue issue, BindingResult result){
        issue.getArticles().add(new Article());
        return "/issues/new";
    }

    //Dinamically remove an article
    @RequestMapping(value = "removearticle", params = {"removeArticle"})
    public String removeArticle(Issue issue, BindingResult result, HttpServletRequest req){
        Integer rowId = Integer.valueOf(req.getParameter("removeArticle"));
        issue.getArticles().remove(rowId.intValue());
        return "/issues/new";
    }
}
