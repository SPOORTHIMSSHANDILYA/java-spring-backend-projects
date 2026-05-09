package com.acc.libraryManagement.repository;

import com.acc.libraryManagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //Page<Book> findAll(Pageable pageable); inherited from JpaRepository — no need to declare it

    @Query(value = "select * " +
            "from books b " +
            "where b.title = :title " +
            "and b.genre = :genre " +
            "and b.author_id = :id", nativeQuery = true)
    List<Book> searchBooks(@Param("title") String title,
                           @Param("genre") String genre,
                           @Param("id") Long id);

    @Query(value = "select * " +
            "from books b " +
            "where b.available_copies > 0", nativeQuery = true)
    List<Book> availableBooks();
}