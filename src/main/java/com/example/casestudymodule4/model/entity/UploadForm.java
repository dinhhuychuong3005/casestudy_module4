package com.example.casestudymodule4.model.entity;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
    private MultipartFile[] files;
    private String description;

    public UploadForm() {
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
