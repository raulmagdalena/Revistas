package com.revistas.repositories;

import com.revistas.entities.Magazine;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.StringTokenizer;

public interface MagazineRepository extends Repository<Magazine, Long> {

    public List<Magazine> findAll();
    public Magazine save(Magazine magazine);
    public String findByMagazineName(String magazineName);
    public Magazine findByIdMagazine(Long idMagazine);
    public Long count();
}
