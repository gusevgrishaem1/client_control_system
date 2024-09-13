package com.techls.ofd.services.taxationsystem;

import com.techls.ofd.entities.TaxationSystem;

import java.util.List;

public interface TaxationSystemService {
    Long create(TaxationSystem taxationSystem);

    void update(TaxationSystem taxationSystem);

    void delete(Long id);

    void recover(Long id);

    List<TaxationSystem> getAll();

    List<TaxationSystem> getCombo();
}
