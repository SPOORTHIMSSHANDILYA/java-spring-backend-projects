package com.acc.libraryManagement.services;

import com.acc.libraryManagement.entities.Author;
import com.acc.libraryManagement.mapper.AuthorMapper;
import com.acc.libraryManagement.mapper.BookMapper;
import com.acc.libraryManagement.models.AuthorRequestDTO;
import com.acc.libraryManagement.models.AuthorResponseDTO;
import com.acc.libraryManagement.models.BooksResponseDTO;
import com.acc.libraryManagement.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final BookMapper bookMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, BookMapper bookMapper){
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    public List<AuthorResponseDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    public AuthorResponseDTO getAuthorsById(int id){
        Author author =  authorRepository.getAuthorsById(id);
        return authorMapper.toDTO(author);
    }

    public List<BooksResponseDTO> getBooksByAuthorId(int id) {
        return authorRepository.getBooksByAuthorId(id)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Transactional
    public AuthorResponseDTO saveAuthor(AuthorRequestDTO authorRequest) {
        Author author = authorMapper.toEntity(authorRequest);
        authorRepository.save(author);
        return authorMapper.toDTO(author);
    }

    @Transactional
    public AuthorResponseDTO updateAuthor(int id, AuthorRequestDTO authorRequest){
        Author existingAuthor = authorRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Author not found: " + id));
        existingAuthor.setFirstName(authorRequest.getFirstName());
        existingAuthor.setLastName(authorRequest.getLastName());
        existingAuthor.setEmail(authorRequest.getEmail());
        existingAuthor.setNationality(authorRequest.getNationality());
        existingAuthor.setBio(authorRequest.getBio());
        authorRepository.save(existingAuthor);
        return authorMapper.toDTO(existingAuthor);
    }

    @Transactional
    public String deleteAuthor(int id){
        Author existingAuthor = authorRepository.findById((long) id).orElseThrow(() -> new RuntimeException("Author not found: " + id));
        authorRepository.deleteById(existingAuthor.getId());
        return "Deleted author id : "+id+" successfully";
    }
}
