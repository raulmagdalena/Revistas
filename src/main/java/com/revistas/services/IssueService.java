package com.revistas.services;

import com.revistas.entities.Issue;
import com.revistas.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    IssueRepository repository;

    public List<Issue> getAllIssuePage( Integer page, Integer size, Long idMagazine){
        Pageable paging = PageRequest.of(page, size);
        Page<Issue> pageResult = repository.findByIdMagazineOrderByNumberASC(idMagazine, paging);
        if(pageResult.hasContent()){
            return pageResult.getContent();
        }else{
            return new ArrayList<Issue>();
        }
    }

}
