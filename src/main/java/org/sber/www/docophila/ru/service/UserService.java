package org.sber.www.docophila.ru.service;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> addUser(String fio, String password, String email);

    ResponseEntity<?> deleteUser(String fio, String password);
}
