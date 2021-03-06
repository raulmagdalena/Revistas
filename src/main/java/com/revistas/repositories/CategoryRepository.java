package com.revistas.repositories;

import com.revistas.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CategoryRepository extends Repository<Category, Long> {

    @Query(value = "SELECT `category_name` FROM `categories` WHERE `category_name`LIKE %:term%", nativeQuery = true)
    List<String> getCategories(String term);

    public Category findByCategoryName(String categoryName);
    public List<Category> findAll();
    public Category save(Category category);
    public Long count();
    public Category findByIdCategory(Long idCategory);
}

