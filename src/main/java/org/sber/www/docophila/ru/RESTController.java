package org.sber.www.docophila.ru;

import org.sber.www.docophila.ru.service.FileService;
import org.sber.www.docophila.ru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class RESTController {

    private FileService fileService;
    private UserService userService;


    @Autowired
    RESTController(FileService fileService, UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        return fileService.uploadFile(uploadfile);
    }

    @PostMapping("/users/registration")
    public ResponseEntity<?> addUser(@RequestParam(value = "fio") String fio,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "email") String email) {

        return userService.addUser(fio, password, email);
    }

    @DeleteMapping("/users/remove")
    public ResponseEntity<?> dellUser(@RequestParam(value = "fio") String fio,
                                      @RequestParam(value = "password") String password) {
        return userService.deleteUser(fio, password);
    }


    /*@ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/
}