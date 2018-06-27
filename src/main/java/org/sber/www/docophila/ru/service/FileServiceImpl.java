package org.sber.www.docophila.ru.service;

import org.sber.www.docophila.ru.OCR.OCR;
import org.sber.www.docophila.ru.model.Document;
import org.sber.www.docophila.ru.model.User;
import org.sber.www.docophila.ru.repository.DocumentRepository;
import org.sber.www.docophila.ru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {

    private DocumentRepository documentRepository;
    private UserRepository userRepository;


    @Autowired
    FileServiceImpl(UserRepository userRepository, DocumentRepository documentRepository){
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }


    public ResponseEntity<?> uploadFile(MultipartFile uploadfile) {

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
        }

        String[] buf = uploadfile.getContentType().split("/");
        String format = buf[buf.length - 1].toLowerCase();

        String outStr = null;
        try {
            switch (format) {
                case "pdf": {
                    outStr = OCR.toOCR(OCR.PdfToPngList(uploadfile.getBytes()), "rus");
                    break;
                }
                case "png": {
                    BufferedImage image = OCR.createImageFromBytes(uploadfile.getBytes());
                    outStr = OCR.toOCR(image, "rus");
                    break;
                }
                default: {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            saveUploadedFiles(uploadfile);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        return new ResponseEntity<>(outStr, HttpStatus.OK);

    }




    private void saveUploadedFiles(MultipartFile file, User user) throws IOException {
        String filename = file.getOriginalFilename();
        byte[] document = file.getBytes();
        Document doc = Document.builder().file(document).filename(filename).user(user).inprogress(true).build();
        documentRepository.save(doc);
    }
}