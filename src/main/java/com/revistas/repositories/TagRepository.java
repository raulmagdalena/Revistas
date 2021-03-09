package com.revistas.repositories;

import com.revistas.entities.Tag;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TagRepository extends Repository<Tag, Long> {

    List<Tag> findAll();
}
