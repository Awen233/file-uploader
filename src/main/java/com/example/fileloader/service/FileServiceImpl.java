package com.example.fileloader.service;
import com.example.fileloader.FileloaderApplication;
import com.example.fileloader.dao.FileDAO;
import com.example.fileloader.entity.FileDTO;
import com.example.fileloader.entity.FileMeta;
import com.example.fileloader.exceptions.MyFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;
    private static final Logger logger= LoggerFactory.getLogger(FileloaderApplication.class);


    @Value("${file.location}")
    String file_location;

    @Autowired
    public FileServiceImpl(FileDAO fileDao){
        this.fileDAO = fileDao;
    }

    @Override
    public FileDTO uploadFile(MultipartFile file) {
        logger.info("start uploadFile service");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        long size = file.getSize();
        FileMeta fileMeta = new FileMeta(fileName, fileType, size);
        FileMeta meta = storeFileMeta(fileMeta);

        Path path = Paths.get(file_location + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
        return new FileDTO(meta.getId(), fileDownloadUri);
    }

    @Transactional
    public FileMeta storeFileMeta(FileMeta fileMeta){
        logger.info("store the file's meta data");
        return fileDAO.save(fileMeta);
    }


    @Transactional
    @Override
    public FileMeta getMetaById(long id) {
        logger.info("start the get meta data service");
        Optional<FileMeta> res = fileDAO.findById(id);
        if(!res.isPresent()){
            throw new MyFileNotFoundException("meta data with id " + id + " not found");
        }
        FileMeta meta = res.get();
        return meta;
    }

    @Override
    public File loadFileAsResource(String fileName){
        logger.info("start loading the resources");
        String fileURL = file_location + fileName;
        File file = new File(fileURL);
        return file;
    }

    @Transactional
    @Override
    public List<FileMeta> findFileMetaBiggerOrEqual(long id) {
        List<FileMeta> res = fileDAO.findFileMetaBiggerOrEqual(id);
        return res;
    }
}
