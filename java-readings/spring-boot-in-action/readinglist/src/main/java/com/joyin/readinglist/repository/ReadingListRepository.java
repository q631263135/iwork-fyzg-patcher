package com.joyin.readinglist.repository;

import com.joyin.readinglist.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
