package com.example.fileloader.controller;


import com.example.fileloader.entity.FileDTO;
import com.example.fileloader.entity.FileMeta;
import com.example.fileloader.exceptions.MyFileNotFoundException;
import com.example.fileloader.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;

@RestController
public class FileUploadController {

    private final FileService service;

    @Autowired
    public FileUploadController(FileService service){
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file")MultipartFile file){
        FileDTO dto = service.uploadFile(file);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/meta/{id}")
    public ResponseEntity<FileMeta> getMetaById(@PathVariable long id){
        FileMeta meta = service.getMetaById(id);
        return new ResponseEntity(meta, HttpStatus.OK);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        File file = service.loadFileAsResource(fileName);
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            throw new MyFileNotFoundException("file not found");
        }
    }

    @GetMapping("/files/criteria/{id}")
    public ResponseEntity<List<FileMeta>> findFileMetaBiggerOrEqual(@PathVariable long id){
        List<FileMeta> res = service.findFileMetaBiggerOrEqual(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
