package com.revistas.controllers;

import com.revistas.entities.Article;
import com.revistas.entities.Author;
import com.revistas.entities.Issue;
import com.revistas.exceptions.IssueNoIdException;
import com.revistas.exceptions.IssueNotFoundException;
import com.revistas.repositories.ArticleRepository;
import com.revistas.repositories.AuthorRepository;
import com.revistas.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.repository = articleRepository;
    }

    //Get all articles
    @GetMapping("/all")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", repository.findAll());
        return "/articles/articles";
    }

    //Create a new article
    @GetMapping("/new/{idIssue}")
    public String newArticleForm(@PathVariable(required = true) Long idIssue, Model model) {
        if (idIssue != null) {
            try {
                Issue issue = issueRepository.findByIdIssue(idIssue);
                model.addAttribute("article", new Article());
                model.addAttribute("issue", issue);
                return "articles/addarticle";
            } catch (EmptyResultDataAccessException e) {
                throw new IssueNotFoundException(idIssue);
            }
        } else {
            throw new IssueNoIdException();
        }
    }

    //Save the article
    @PostMapping(value = "/savearticle")
    public String saveArticle(@ModelAttribute Article article, BindingResult result, @RequestParam("authors") String strAuthors,
                              @RequestParam("idIssue") Long idIssue){
        //TODO envoltar tota la lógica en un try, per que no es guardi un autor, si no es guarda el article
        if(result.hasErrors()){
            //Long id = Long.parseLong(idIssue);
            for (String author : strAuthors.split(",")){
                if(author != ""){
                    String trimAuthor;
                    trimAuthor = author.trim();
                    Author newAuthor = authorRepository.findByAuthorName(trimAuthor);
                    if(newAuthor == null){
                        newAuthor = new Author();
                        newAuthor.setAuthorName(trimAuthor);
                        authorRepository.save(newAuthor);
                    }
                    article.addAuthor(newAuthor);
                    article.setIssue(issueRepository.findByIdIssue(idIssue));
                }
            }
            //TODO comprovar que no existeix un article amb el mateix ordre o títol
            repository.save(article);
            return "redirect:/issues/issue/" + idIssue;
        }
        return "redirect:/articles/new";
    }

    //Show article by id
    @GetMapping(value = "/article/{idArticle}")
    public ModelAndView getArticleById(@PathVariable Long idArticle){
        try {
            ModelAndView modelAndView = new ModelAndView("articles/article");
            Article article = repository.findById(idArticle);
            modelAndView.addObject("article", article);
            Issue issue = issueRepository.findByIdIssue(article.getIdIssue());
            modelAndView.addObject("issue", issue);
            return modelAndView;
        } catch (EmptyResultDataAccessException e){
            throw new IssueNotFoundException(idArticle);
        }
    }
}