package com.revistas.controllers;

import com.revistas.services.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(value = "/covers")
public class CoverController {

    @Autowired
    public CoverService coverService;

    @PostMapping(value = "upload")
    public ResponseEntity uploadCover(@RequestParam MultipartFile cover){
        return this.coverService.uploadToFileSystem(cover);
    }

    @GetMapping(value = "getCover/{coverName:.+}", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "coverName") String fileName) throws IOException{
        return this.coverService.getImageWithMediaType(fileName);
    }

}
