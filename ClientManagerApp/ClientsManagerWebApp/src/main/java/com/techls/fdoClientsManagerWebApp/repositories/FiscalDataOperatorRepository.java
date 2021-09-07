package com.techls.fdoClientsManagerWebApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperator;


public interface FiscalDataOperatorRepository extends JpaRepository<FiscalDataOperator, Long> {
	public FiscalDataOperator findById(long id);
	public List<FiscalDataOperator> findAll();
}
