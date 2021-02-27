package com.revistas.repositories;

import com.revistas.entities.Issue;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IssueRepository extends Repository<Issue, Long> {

    public List<Issue>  findAll();
    public Issue save(Issue issue);
    public Issue findByIdIssue(Long idIssue);
}
