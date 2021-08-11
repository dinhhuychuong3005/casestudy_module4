package com.example.casestudymodule4.model.entity;

import org.springframework.web.multipart.MultipartFile;

public class UserForm {
  private MultipartFile file;

  public UserForm() {
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }
}
