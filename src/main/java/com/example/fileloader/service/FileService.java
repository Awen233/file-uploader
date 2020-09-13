package com.example.fileloader.service;

import com.example.fileloader.entity.FileMeta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public String uploadFile(MultipartFile file);

    public FileMeta getMetaById(long id);

    public String downloadFile(String fileName);

    public List<FileMeta> findFileMetaBiggerOrEqual(long id);

}
