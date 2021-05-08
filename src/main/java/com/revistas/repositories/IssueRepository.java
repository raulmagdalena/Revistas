package com.revistas.repositories;

import com.revistas.entities.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IssueRepository extends Repository<Issue, Long> {

    public List<Issue>  findAll();
    public Issue save(Issue issue);
    public Issue findByIdIssue(Long idIssue);
    @Query(value = "SELECT * FROM issue WHERE id_magazine= ?1 ORDER BY issue_number DESC", nativeQuery = true)
    public Page<Issue> findByIdMagazineOrderByNumberASC(long idMagazine, Pageable pageable);
    public Long count();
}
