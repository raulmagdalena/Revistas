package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_issue")
    private Long idIssue;

    @Column(name = "issue_number", unique = true, nullable = false)
    private String number;

    @Column(name = "issue_pages", nullable = true)
    private Integer pages;

    @Column(name = "issue_date", nullable = true)
    private Date issueDate;

    @Column(name = "issue_cover")
    private String cover;

    @Pattern(regexp = "[\\S]{4}\\-[\\S]{4}\\-[\\s]{5}")
    @Column(name = "issue_issn")
    private String issueIssn;

    @ManyToOne(targetEntity = Magazine.class)
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;

    @CreationTimestamp
    @Column(name = "create_date_time")
    private java.sql.Timestamp  createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp  updateDateTime;


    public Long getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Long idIssue) {
        this.idIssue = idIssue;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIssueIssn() {
        return issueIssn;
    }

    public void setIssueIssn(String issueIssn) {
        this.issueIssn = issueIssn;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
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

