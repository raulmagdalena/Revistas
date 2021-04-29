package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collection")
    private Long idCollection;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Magazine magazine;

    @OneToMany(targetEntity = Issue.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "collection")
    private List<Issue> issues = new ArrayList<Issue>();

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;


    public Long getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Long idCollection) {
        this.idCollection = idCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
