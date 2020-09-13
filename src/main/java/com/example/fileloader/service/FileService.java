package com.example.fileloader.service;

import com.example.fileloader.entity.FileDTO;
import com.example.fileloader.entity.FileMeta;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {

    public FileDTO uploadFile(MultipartFile file);

    public FileMeta getMetaById(long id);

    public List<FileMeta> findFileMetaBiggerOrEqual(long id);

    public File loadFileAsResource(String fileName);

}
