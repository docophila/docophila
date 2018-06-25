package org.sber.www.docophila.ru;

import org.sber.www.docophila.ru.bean.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class RESTController {

    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

        return fileService.uploadFile(uploadfile);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() {

        return fileService.getHistory();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFile() {

        return fileService.searchFile();
    }

    /*@ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }*/
}