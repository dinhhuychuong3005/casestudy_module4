package com.example.casestudymodule4.model.entity;

import org.springframework.web.multipart.MultipartFile;

public class UploadPost {
    private MultipartFile [] files;
    private String content;

    public UploadPost() {
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
