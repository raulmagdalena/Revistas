package com.revistas.repositories;

import com.revistas.entities.Collection;
import org.springframework.data.repository.Repository;

public interface CollectionRepository extends Repository<Collection, Long> {

    public Collection save(Collection collection);
    public Collection findById(Long idCollection);
}
