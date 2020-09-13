package com.example.fileloader.service;
import com.example.fileloader.dao.FileDAO;
import com.example.fileloader.entity.FileMeta;
import com.example.fileloader.exceptions.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    FileDAO fileDAO;

    @Value("${file.location}")
    String file_location;

    @Autowired
    public FileServiceImpl(FileDAO fileDao){
        this.fileDAO = fileDao;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        long size = file.getSize();
        FileMeta fileMeta = new FileMeta(fileName, fileType, size);
        fileDAO.save(fileMeta);

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
        return fileDownloadUri;
    }

    @Override
    public FileMeta getMetaById(long id) {
        Optional<FileMeta> res = fileDAO.findById(id);
        if(!res.isPresent()){
            throw new FileNotFoundException("meta data with id " + id + " not found");
        }
        FileMeta meta = res.get();
        return meta;
    }

    @Override
    public String downloadFile(String fileName) {
        String fileLocation = file_location + fileName;
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileLocation);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder data = new StringBuilder();
        try{
            while ((line = br.readLine()) != null) {
                data.append(line);
                data.append("\n");
            }
        } catch (java.io.IOException e){
            e.printStackTrace();
        }
        return data.toString();
    }

    @Override
    public List<FileMeta> findFileMetaBiggerOrEqual(long id) {
        List<FileMeta> res = fileDAO.findFileMetaBiggerOrEqual(id);
        return res;
    }
}
