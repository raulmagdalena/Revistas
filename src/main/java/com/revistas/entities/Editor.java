package com.revistas.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "editor")
public class Editor {

    @Id
    @Column(name = "id_editor")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEditor;

    @NotBlank(message = "Editor name is mandatory")
    @Column(name = "editor_name", unique = true)
    private String editorName;

    @OneToMany(targetEntity = Magazine.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "editor")
    private List<Magazine> magazines = new ArrayList<Magazine>();

    //TODO add fields for email, phone number, vat number

    @CreationTimestamp
    @Column(name = "create_date_time")
    private java.sql.Timestamp createDateTime;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private java.sql.Timestamp updateDateTime;

    public Long getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(Long idEditor) {
        this.idEditor = idEditor;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    public java.sql.Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(java.sql.Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public java.sql.Timestamp  getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(java.sql.Timestamp  updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}

