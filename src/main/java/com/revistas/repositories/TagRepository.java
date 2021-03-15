package com.revistas.repositories;

import com.revistas.entities.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TagRepository extends Repository<Tag, Long> {

    @Query(value = "SELECT `tag_name` FROM `tags` WHERE `tag_name`LIKE %:term%", nativeQuery = true)
    List<String> getTags(String term);

    List<Tag> findAll();
}
