package com.acc.libraryManagement.repository;

import com.acc.libraryManagement.entities.Author;
import com.acc.libraryManagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    List<Author> findAll(); - Spring automatically provides this

    @Query(value = "Select * from authors where id = :id",nativeQuery = true)
    Author getAuthorsById(@Param("id") int id);

    @Query(value = "Select * from books where author_id = :id", nativeQuery = true)
    List<Book> getBooksByAuthorId(@Param("id") int id);

    @Query(value = "Select * from authors where first_name = :firstName and last_name = :secondName ;", nativeQuery = true)
    Author getAuthorIdByName(@Param("firstName") String firstName, @Param("secondName") String secondName);
}