package com.example.casestudymodule4.controller;
import com.example.casestudymodule4.model.entity.*;
import com.example.casestudymodule4.model.entity.Comment;
import com.example.casestudymodule4.model.entity.Friend;
import com.example.casestudymodule4.model.entity.Post;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.friend.IFriendService;
import com.example.casestudymodule4.service.image.ImageServiceImpl;
import com.example.casestudymodule4.service.post.IPostService;
import com.example.casestudymodule4.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IFriendService friendService;
    @Autowired
    private ImageServiceImpl imageService;


    private final Logger logger = LoggerFactory.getLogger(PostController.class);



    //Save the uploaded file to this folder
    private static String UPLOAD_DIR = "img";


//    @GetMapping("/test")
//    public ModelAndView test(){
//        return new ModelAndView("/test");
//    }

    //maps html form to a Model
    @PostMapping("/create")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadPost form) {
        Post post = new Post();
        postService.save(post);
        logger.debug("Multiple file upload! With UploadModel");
        String result = null;
        try {

            result = this.saveUploadedFiles(form.getFiles());

            post.setContent(form.getContent());
            MultipartFile[] files = form.getFiles();
            List<Image> imgs = new ArrayList<>();

            for(MultipartFile file : files){
                if (file.isEmpty()) {
                    continue;
                }
                Image image = new Image(post.getId(),UPLOAD_DIR+"/"+file.getOriginalFilename());
                imgs.add(image);
                imageService.save(image);
            }
            post.setImgs(imgs);
            post.setDatePost(new Date(Calendar.getInstance().getTime().getTime()));
            post.setQuantityLike(0);
            post.setQuantityComment(0);
            post.setView(0);
            post.setStatus(1);
            post.setUser(form.getUser());
            postService.save(post);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);

    }


    // Save Files
    private String saveUploadedFiles(MultipartFile[] files) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(UPLOAD_DIR);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("<br/>");
        }
        return sb.toString();
    }


    @GetMapping("")
    public ResponseEntity<Iterable<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    public List<User> getListUserFriend(Long idUser) {
        List<Friend> listFriend = friendService.findAllFriendById(idUser);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity : listFriend) {

            if (friendEntity.getStatus() == 1) {
                com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getIdFriendOfUser()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

    public List<User> getListUserFriendByFr(Long idFr) {
        List<Friend> listFriend = friendService.findAllFriendByIdFr(idFr);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity : listFriend) {

            if (friendEntity.getStatus() == 1) {
                com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getUser().getId()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

    public List<User> showListFriend(Long id) {
        List<User> users = getListUserFriend(id);
        List<User> users1 = getListUserFriendByFr(id);
        for (int i = 0; i < users.size(); i++) {
            users1.add(users.get(i));
        }
        return users1;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Post>> findAllByIdUs(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<Iterable<Post>> findAllByIdUsAndByView(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findAllByUserIdAndByStatus(id), HttpStatus.OK);
    }

    @GetMapping("/listPostFriend/{id}")
    public ResponseEntity<Iterable<Post>> getAllPostFriend(@PathVariable Long id) {
        List<User> users = showListFriend(id);
        List<Post> posts ;
        List<Post> posts1 = new ArrayList<>();
        List<Post> postUser = (List<Post>) postService.findAllByUserIdAndByStatus(id);
        for (int i = 0; i < postUser.size(); i++) {
            posts1.add(postUser.get(i));
        }
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getId());
                posts = (List<Post>) postService.findAllByUserIdAndByStatus(users.get(i).getId());
            if (postService.findAllByUserIdAndByStatus(users.get(i).getId()) != null) {
                for (int j = 0; j < posts.size(); j++) {
                    posts1.add(posts.get(j));
                }
            }

        }
        return new ResponseEntity<>(posts1, HttpStatus.OK);
    }
    @GetMapping("/views/{id}")
    public ResponseEntity<Post> checkView(@PathVariable Long id){
        Optional<Post> post = postService.findById(id);
        post.get().setView(post.get().getView()+1);
        postService.save(post.get());
        return new ResponseEntity<>(post.get(),HttpStatus.OK);
    }


}

