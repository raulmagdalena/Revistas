package com.revistas.controllers;

import com.revistas.entities.Magazine;
import com.revistas.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;

    @RequestMapping("/")
    public String index(Model model){
        Long magazinesCount = magazineRepository.count();
        model.addAttribute("magazinesCount", magazinesCount);
        return "index.html";

    }
}