package com.revistas.repositories;

import com.revistas.entities.Editor;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EditorRepository extends Repository<Editor, Long> {

    public List<Editor> findAll();
    public Editor save(Editor editor);
    public String findByeditorName(String editorName);
    public Editor findById(Long id);
    public Long deleteById(Long id);
    public Editor getOne(long id);
}

