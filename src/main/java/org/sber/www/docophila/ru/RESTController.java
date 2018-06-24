package org.sber.www.docophila.ru;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.sber.www.docophila.ru.bean.FileService;
import org.sber.www.docophila.ru.exceptions.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

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
}