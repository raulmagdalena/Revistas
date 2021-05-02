package com.revistas.services;

import com.revistas.entities.Cover;
import com.revistas.entities.Issue;
import com.revistas.repositories.CoverRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class CoverService {

    @Autowired
    private CoverRepository repository;

    @Value("${app.upload.dir:${user.home}}")
    public String storageDirectoryPath;

    public Cover uploadToFileSystem(MultipartFile file, Issue issue) throws IOException {
        try {
            Path copyLocation = Paths
                    .get(storageDirectoryPath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            Cover newCover = new Cover();
            newCover.setFileName(file.getOriginalFilename());
            newCover.setIssue(issue);
            repository.save(newCover);
            return newCover;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getImageWithMediaType(String coverName) throws IOException{
        Path destination = Paths.get(storageDirectoryPath + "/" + coverName);
        return IOUtils.toByteArray(destination.toUri());
    }
}
