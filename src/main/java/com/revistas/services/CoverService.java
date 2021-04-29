package com.revistas.services;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CoverService {

    public final String storageDirectoryPath = "\\resources\\img";

    public String uploadToFileSystem(String cover){
        // extract the file name of the cover image
        String fileName = StringUtils.cleanPath(cover);
        // the path for the place where the cover is stored
        Path storageDirectory = Paths.get(storageDirectoryPath);
        // check if the folders exists
        if (!Files.exists(storageDirectory)){
            try {
                Files.createDirectories(storageDirectory);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Path origen = Paths.get(cover);
        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);
        try {
            Files.copy(origen,destination);
        } catch (IOException e){
            e.printStackTrace();
        }
        // the response will be the url
        String fileDownloadedUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resources/img")
                .path(fileName)
                .toUriString();
        return fileDownloadedUri;
    }

    public byte[] getImageWithMediaType(String coverName) throws IOException{
        Path destination = Paths.get(storageDirectoryPath + "\\" + coverName);
        return IOUtils.toByteArray(destination.toUri());
    }
}
