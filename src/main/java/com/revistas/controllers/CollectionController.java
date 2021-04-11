package com.revistas.controllers;

import com.revistas.repositories.IssueRepository;
import com.revistas.repositories.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CollectionController {
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private IssueRepository issueRepository;

    public CollectionController(MagazineRepository magRep, IssueRepository issRep){
        this.magazineRepository = magRep;
        this.issueRepository = issRep;
    }
}
