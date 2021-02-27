package com.revistas.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "id_article")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "articles")
    private List<Author> authors;

    public Article(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
