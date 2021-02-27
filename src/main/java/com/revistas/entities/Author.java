package com.revistas.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Long idAuthor;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rel_authors_articles", joinColumns = @JoinColumn(name = "fk_author", nullable = false), inverseJoinColumns = @JoinColumn(name = "fk_article", nullable = false))
    private List<Article> articles;

    //TODO add fields for biography


    public Author(String authorName, List<Article> articles) {
        this.authorName = authorName;
        this.articles = articles;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
