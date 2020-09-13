package com.example.fileloader.entity;

import java.util.Date;

public class FileDTO {

    private long id;
    private String downloadURL;
    private Date date;

    public FileDTO(long id, String downloadURL) {
        this.id = id;
        this.downloadURL = downloadURL;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
