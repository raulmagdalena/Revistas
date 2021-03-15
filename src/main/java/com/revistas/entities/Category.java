package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "category_magazine", joinColumns = @JoinColumn(name = "id_category"), inverseJoinColumns = @JoinColumn(name = "id_magazine"))
    private Set<Magazine> magazines = new HashSet<>();

    @CreationTimestamp
    @Column(name = "creation_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;

    public Category() {
    }

    public Category(String name){
        this.categoryName = name;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Magazine> getMagazines() {
        return this.magazines;
    }

    public void setMagazines(Set<Magazine> magazines) {
        this.magazines = magazines;
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
