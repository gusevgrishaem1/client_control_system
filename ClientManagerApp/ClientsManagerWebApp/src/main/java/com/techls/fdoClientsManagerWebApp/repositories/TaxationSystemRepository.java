package com.techls.fdoClientsManagerWebApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techls.fdoClientsManagerWebApp.entities.TaxationSystem;

public interface TaxationSystemRepository extends JpaRepository<TaxationSystem, Long> {
	public TaxationSystem findById(long id);
	public List<TaxationSystem > findAll();
}
