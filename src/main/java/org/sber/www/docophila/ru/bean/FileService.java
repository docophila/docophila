package org.sber.www.docophila.ru.bean;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public ResponseEntity<?> uploadFile(MultipartFile uploadfile);

    public ResponseEntity<?> getHistory();

    public ResponseEntity<?> searchFile();
}
