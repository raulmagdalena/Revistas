package com.revistas.repositories;

import com.revistas.entities.Cover;
import org.springframework.data.repository.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CoverRepository extends Repository<Cover, Long> {

    public List<Cover> findAll();
    public Cover save(Cover cover);
    public Cover findByIdCover(Long idCover);
}

