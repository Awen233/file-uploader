package com.example.fileloader.controller;


import com.example.fileloader.entity.FileMeta;
import com.example.fileloader.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class FileUploadController {

    FileService service;

    @Autowired
    public FileUploadController(FileService service){
        this.service = service;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        String downloadUrl = service.uploadFile(file);
        return  new ResponseEntity<>(downloadUrl, HttpStatus.OK);
    }

    @GetMapping("/meta/{id}")
    public ResponseEntity<FileMeta> getMetaById(@PathVariable long id){
        FileMeta meta = service.getMetaById(id);
        return new ResponseEntity(meta, HttpStatus.OK);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<String> downloadFile(@PathVariable String fileName) {
        String res = service.downloadFile(fileName);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/files/biggerOrEqual/{id}")
    public ResponseEntity<List<FileMeta>> findFileMetaBiggerOrEqual(@PathVariable long id){
        List<FileMeta> res = service.findFileMetaBiggerOrEqual(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
