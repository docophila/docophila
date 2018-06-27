package org.sber.www.docophila.ru.repository;

import org.sber.www.docophila.ru.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByFioAndEmail(String fio, String email);
    User findByFioAndPassword(String fio, String password);
    User removeByFioAndPassword(String fio, String password);
}
