package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Long idTag;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany
    @JoinTable(name = "tag_article", joinColumns = @JoinColumn(name = "id_tag"), inverseJoinColumns = @JoinColumn(name = "id_article"))
    private List<Article> tagArticles = new ArrayList<Article>();

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Article> getTagArticles() {
        return tagArticles;
    }

    public void setTagArticles(List<Article> tagArticles) {
        this.tagArticles = tagArticles;
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
