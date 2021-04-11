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
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index(Model model){
        model.addAttribute("magazinesCount", magazineRepository.count());
        model.addAttribute("editorsCount", editorRepository.count());
        model.addAttribute("issuesCount", issueRepository.count());
        model.addAttribute("editorsCount", editorRepository.count());
        model.addAttribute("articlesCount", articleRepository.count());
        model.addAttribute("authorsCount", authorRepository.count());
        model.addAttribute("categoriesCount", categoryRepository.count());
        model.addAttribute("tagsCount", tagRepository.count());
        model.addAttribute("usersCount", userRepository.count());
        return "index.html";

    }
}