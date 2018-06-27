package org.sber.www.docophila.ru.service;

import org.sber.www.docophila.ru.model.User;
import org.sber.www.docophila.ru.repository.DocumentRepository;
import org.sber.www.docophila.ru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private DocumentRepository documentRepository;
    private UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository, DocumentRepository documentRepository){
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }


    @Override
    public ResponseEntity<?> addUser(String fio, String password, String email) {
        if(userRepository.findByFioAndEmail(fio, email) == null) {
            userRepository.save(new User().builder().fio(fio).email(email).password(password).build());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(String fio, String password) {
        if(userRepository.findByFioAndPassword(fio, password) != null){
            userRepository.removeByFioAndPassword(fio, password);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
