package com.example.fileloader.dao;

import com.example.fileloader.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileDAO extends JpaRepository<FileMeta, Long> {

    @Query("select s from FileMeta s where s.id >= ?1")
    public List<FileMeta> findFileMetaBiggerOrEqual(long id);

}
