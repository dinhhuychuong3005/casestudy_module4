package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.Avatar;
import com.example.casestudymodule4.model.entity.Image;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.model.entity.UserForm;
import com.example.casestudymodule4.service.Avatar.AvatarServiceImpl;
import com.example.casestudymodule4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    private static final String UPLOAD_DIR = "img";
    @Autowired
    private IUserService userService;
    @Autowired
    private AvatarServiceImpl avatarService;

    @GetMapping
    public ModelAndView hehe(){
        return new ModelAndView("/test");
    }

    @PutMapping("/editPassword/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id,@RequestBody User user){
        Optional<User> user1 = userService.findById(id);
        user.setId(user1.get().getId());
        user.setUsername(user1.get().getUsername());
        user.setAddress(user1.get().getAddress());
        user.setEmail(user1.get().getEmail());
        user.setDateOfBirth(user1.get().getDateOfBirth());
        user.setFullName(user1.get().getFullName());
        user.setGender(user1.get().getGender());
        user.setNumberPhone(user1.get().getNumberPhone());
        user.setRoles(user1.get().getRoles());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/edit-avatar/{id}")
    public ResponseEntity<?> editAvatar(@ModelAttribute UserForm form,@PathVariable Long id) {
        Optional<Avatar> avatarOptional = avatarService.findById(id);
        Avatar avatar = new Avatar();
        if(!avatarOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String result = null;
        try {
            result = this.saveUploadedFiles(form.getFile());
            if(form.getFile()==null){
                avatarOptional.get().setUrl(avatarOptional.get().getUrl());
            } else {
                avatar.setUrl(UPLOAD_DIR+"/"+form.getFile().getOriginalFilename());
                avatarOptional.get().setStatus(0);
            }
            avatar.setUser(form.getUser());
            avatarService.save(avatar);
            avatarService.save(avatarOptional.get());
            }

        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);

    }

    // Save Files
    private String saveUploadedFiles(MultipartFile file) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();
            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
            sb.append(uploadFilePath).append("<br/>");
        return sb.toString();
    }
}


