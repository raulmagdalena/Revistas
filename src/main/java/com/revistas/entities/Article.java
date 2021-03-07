package com.revistas.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private List<Author> authors = new ArrayList<Author>();

    @ManyToMany(mappedBy = "tagArticles")
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(name = "id_issue")
    private Issue issue;

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;


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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
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
