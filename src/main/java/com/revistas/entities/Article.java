package com.revistas.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "id_article")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "article_order", unique = true, nullable = false)
    private Integer order;

    @ManyToMany(mappedBy = "articles",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(name = "id_issue")
    private Issue issue;

    //TODO into an article, persons, concepts or agents as named, try to create a class for that and link it with info at wikidata

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;

    public Article(){}

    public Article(String title, Set<Author> authors) {
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
        author.getArticles().add(this);
    }

    public Issue getIssue(Long idIssue) {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Long getIdIssue(){
        return this.issue.getIdIssue();
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
