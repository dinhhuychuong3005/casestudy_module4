package com.example.casestudymodule4.model.entity;

import org.springframework.web.multipart.MultipartFile;

public class UserForm {
  private MultipartFile file;
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public UserForm() {
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }
}
