package com.gurkha.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FileController{

    @Value("${uploads.cv}")
    private  String cvPath;

    @Value("${uploads.blog}")
    private  String blogPath;

    @GetMapping("/cv/{filename}")
    public ResponseEntity<Resource> getCv(@PathVariable String filename){
        try {


            Path filePath = Paths.get(cvPath).resolve(filename).normalize();
            Resource resources = new UrlResource(filePath.toUri());
            if(!resources.exists()){
                return  ResponseEntity.notFound().build();
            }
            return  ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + ((UrlResource) resources).getFilename()+ "\" ")
                    .body(resources);
        }catch (Exception ex){
            return  ResponseEntity.internalServerError().build();

        }
    }
    @GetMapping("/blog/{filename}")
    public ResponseEntity<Resource> getBlogImg(@PathVariable String filename){
        try {

            Path filePath = Paths.get(blogPath).resolve(filename).normalize();
            Resource resources = new UrlResource(filePath.toUri());
            if(!resources.exists()){
                return  ResponseEntity.notFound().build();
            }
            return  ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + ((UrlResource) resources).getFilename()+ "\" ")
                    .body(resources);
        }catch (Exception ex){
            return  ResponseEntity.internalServerError().build();

        }

    }

}