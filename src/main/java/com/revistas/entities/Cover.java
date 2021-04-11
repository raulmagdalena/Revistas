package com.revistas.entities;

import javax.persistence.*;

@Entity
@Table(name = "cover")
public class Cover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cover")
    private Long idCover;

    @Column(name = "path")
    private String path;

    @Column(name = "fileName")
    private String fileName;

    @OneToOne(mappedBy = "cover")
    private Issue issue;

    public Long getIdCover() {
        return idCover;
    }

    public void setIdCover(Long idCover) {
        this.idCover = idCover;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
