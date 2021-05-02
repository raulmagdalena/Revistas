package com.revistas.repositories;

import com.revistas.entities.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


import java.util.List;

public interface ArticleRepository extends Repository<Article, Long> {

    List<Article> findAll();
    @Query(value = "SELECT * FROM articles WHERE id_issue = ?1 ORDER BY article_order ASC", nativeQuery = true)
    List<Article> findByIdIssueOrderByArticleOrderASC(long idIssue);
    public Article save(Article article);
    public Article findById(Long idArticle);
    public Long count();
}
