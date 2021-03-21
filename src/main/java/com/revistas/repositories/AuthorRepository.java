package com.revistas.repositories;

import com.revistas.entities.Author;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AuthorRepository extends Repository<Author, Long> {

    public List<Author> findAll();
    public Author findByAuthorName(String authorName);
    public Author save(Author author);
}
