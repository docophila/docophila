package org.sber.www.docophila.ru.bean;

import org.sber.www.docophila.ru.OCR.OCR;
import org.sber.www.docophila.ru.model.Document;
import org.sber.www.docophila.ru.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    DocumentRepository documentRepository;

    //1.Загрузка файла
    public ResponseEntity<?> uploadFile(MultipartFile uploadfile) {

        System.out.println("Single file upload!");
        System.out.println(uploadfile.getContentType());
        System.out.println(uploadfile.getOriginalFilename());
        System.out.println(uploadfile.getName());


        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
        }

        String []buf = uploadfile.getContentType().split("/");
        String format = buf[buf.length-1].toLowerCase();

        String outStr = null;
        try {
            switch (format){
                case "pdf":{
                    outStr = OCR.toOCR(OCR.PdfToPngList(uploadfile.getBytes()),"rus");
                    break;
                }
                case "png":{
                    BufferedImage image = OCR.createImageFromBytes(uploadfile.getBytes());
                    outStr = OCR.toOCR(image, "rus");
                    break;
                }default:{
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

    @Override
    public ResponseEntity<?> getHistory() {
        return null;
    }

    @Override
    public ResponseEntity<?> searchFile() {
        return null;
    }
/*

   //2.Если файл существует сохраняем его
    private void saveUploadedFiles(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        byte[] document = file.getBytes();
        Document doc = new Document(filename, document, true);
        documentRepository.save(doc);
    }*/
}