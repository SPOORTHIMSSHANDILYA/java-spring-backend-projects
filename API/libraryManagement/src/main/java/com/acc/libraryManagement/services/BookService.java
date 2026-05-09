package com.acc.libraryManagement.services;

import com.acc.common_lib.models.PagedResponseDTO;
import com.acc.libraryManagement.entities.Author;
import com.acc.libraryManagement.entities.Book;
import com.acc.libraryManagement.mapper.BookMapper;
import com.acc.libraryManagement.models.BookRequestDTO;
import com.acc.libraryManagement.models.BooksResponseDTO;
import com.acc.libraryManagement.repository.AuthorRepository;
import com.acc.libraryManagement.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public PagedResponseDTO<BooksResponseDTO> getAllBooks(int page, int size, String sortBy, String direction){
        //build sort
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        //build pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        //query db
        Page<Book> pagedBooks = bookRepository.findAll(pageable);

        //map each book to BooksResponseDTO
        List<BooksResponseDTO> books =  pagedBooks.getContent()
                .stream()
                .map(bookMapper::toDto)
                .toList();

        return new PagedResponseDTO<>(
                books,
                pagedBooks.getNumber(),
                pagedBooks.getSize(),
                pagedBooks.getTotalElements(),
                pagedBooks.getTotalPages(),
                pagedBooks.isLast(),
                pagedBooks.isFirst()
        );
    }

    public BooksResponseDTO findBookById(int id){
        Book book = bookRepository.findById((long) id).orElseThrow(()-> new RuntimeException("No Book found with id : "+id));
        return bookMapper.toDto(book);
    }


    public List<BooksResponseDTO> searchBooks(String title, String genre , int authorId){
        List<Book> books = bookRepository.searchBooks(title,genre, (long) authorId);
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BooksResponseDTO> availableBooks(){
        List<Book> books = bookRepository.availableBooks();
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Transactional
    public BooksResponseDTO saveBook(BookRequestDTO bookRequest){
        //get author based on name
        Author author = authorRepository.getAuthorIdByName(bookRequest.getAuthorFirstName(),bookRequest.getAuthorLastName());
        Book book = bookMapper.toEntity(bookRequest);
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public BooksResponseDTO updateBook(BookRequestDTO bookRequest,int id){
        Author author = authorRepository.getAuthorIdByName(bookRequest.getAuthorFirstName(),bookRequest.getAuthorLastName());

        Book existingBook = bookRepository.findById((long) id).orElseThrow(()-> new RuntimeException("No Book found with id : "+id));
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setGenre(bookRequest.getGenre());
        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setPublishedYear(bookRequest.getPublishedYear());
        existingBook.setAvailableCopies(bookRequest.getAvailableCopies());
        existingBook.setTotalCopies(bookRequest.getTotalCopies());
        existingBook.setAuthor(author);

        bookRepository.save(existingBook);

        return bookMapper.toDto(existingBook);
    }

    @Transactional
    public String deleteBook(int id){
        Book existingBook = bookRepository.findById((long) id).orElseThrow(()-> new RuntimeException("No Book found with id : "+id));
        bookRepository.deleteById(existingBook.getId());
        return "Successfully deleted book with id: "+id;
    }
}
