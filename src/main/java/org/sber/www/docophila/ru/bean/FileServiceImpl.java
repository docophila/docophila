package org.sber.www.docophila.ru.bean;

import org.sber.www.docophila.ru.OCR.OCR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    //1.Загрузка файла
    public ResponseEntity<?> uploadFile(MultipartFile uploadfile) {

        System.out.println("Single file upload!");
        System.out.println(uploadfile.getContentType());
        System.out.println(uploadfile.getOriginalFilename());
        System.out.println(uploadfile.getName());


        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
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

            System.out.println(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            saveUploadedFiles(uploadfile);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> getHistory() {
        return null;
    }

/*
   //2.Если файл существует сохраняем его
    private void saveUploadedFiles(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        byte[] document = file.getBytes();
        byte[] bytes = file.getBytes();
        Document doc = Document.builder().email(email).file((byte[]) document).filename(filename).inprogress(true).build();
        documentRepository.save(doc);
    }*/
}