package com.revistas.repositories;

import com.revistas.entities.Author;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends Repository<Author, Long> {

    public Set<Author> findAll();
    public Author findByAuthorName(String authorName);
    public Author save(Author author);
    public Author findById(Long idAuthor);
    public Long count();
}
