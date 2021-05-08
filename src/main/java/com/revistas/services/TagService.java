package com.revistas.services;

import com.revistas.entities.Tag;
import com.revistas.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> getAllTagsPage(Integer page, Integer size){
        Pageable paging = PageRequest.of(page, size);
        Page<Tag> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()){
            return pageResult.getContent();
        }else {
            return new ArrayList<Tag>();
        }
    }
}
