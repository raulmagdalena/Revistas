package com.revistas.controllers;

import com.revistas.entities.Tag;
import com.revistas.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService service;

    @GetMapping("/all")
    public ModelAndView getAllTags(){
        ModelAndView modelAndView = new ModelAndView("tags/tags");
        List<Tag> tags = new ArrayList<>();
        tags = service.getAllTagsPage(1, 10);
        modelAndView.addObject("tags", tags );
        return modelAndView;
    }
}

