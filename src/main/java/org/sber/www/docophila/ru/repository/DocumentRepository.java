package org.sber.www.docophila.ru.repository;

import org.sber.www.docophila.ru.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
