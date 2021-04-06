package com.revistas.repositories;

import com.revistas.entities.Article;
import org.springframework.data.repository.Repository;


import java.util.List;

public interface ArticleRepository extends Repository<Article, Long> {

    List<Article> findAll();
    public Article save(Article article);
    public Article findById(Long idArticle);
}
