package com.example.fileloader.entity;

import javax.persistence.*;

@Entity
@Table(name = "file_meta")
public class FileMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "file_type")
    private String file_type;

    @Column(name = "file_size")
    private long size;


    public FileMeta(){

    }

    public FileMeta(String file_name, String file_type, long size) {
        this.file_name = file_name;
        this.file_type = file_type;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }
}
