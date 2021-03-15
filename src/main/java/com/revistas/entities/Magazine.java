package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "magazine")
public class Magazine {

    @Id
    @Column(name = "id_magazine")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMagazine;
    
    @Column(name = "magazineName", unique = true, nullable = false)
    private String magazineName;

    @Pattern(regexp = "[\\S]{4}\\-[\\S]{4}")
    @Column(name = "isbn", unique = true, nullable = false, length = 9)
    private String magazineIssn;

    @ManyToOne(targetEntity = Editor.class)
    @JoinColumn(name = "id_editor")
    private Editor editor;

    @OneToMany(targetEntity = Issue.class, mappedBy = "magazine")
    private List<Issue> issues = new ArrayList<Issue>();

    @ManyToMany(mappedBy = "magazines")
    private Set<Category> categories = new HashSet<Category>();

    //TODO frequency property
    //TODO language property

    @CreationTimestamp
    @Column(name = "create_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;


    public Long getIdMagazine() {
        return idMagazine;
    }

    public void setIdMagazine(Long idMagazine) {
        this.idMagazine = idMagazine;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getMagazineIssn() {
        return magazineIssn;
    }

    public void setMagazineIssn(String magazineIssn) {
        this.magazineIssn = magazineIssn;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(java.sql.Timestamp  createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(java.sql.Timestamp  updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}