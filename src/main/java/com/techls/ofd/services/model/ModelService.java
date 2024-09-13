package com.techls.ofd.services.model;

import com.techls.ofd.entities.Model;

import java.util.List;

public interface ModelService {
    Long create(Model model);

    void update(Model model);

    void archive(Long id);

    void recover(Long id);

    List<Model> getAll();

    List<Model> getCombo();
}
