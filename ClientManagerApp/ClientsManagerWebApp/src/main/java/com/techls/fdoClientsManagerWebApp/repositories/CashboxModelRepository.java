package com.techls.fdoClientsManagerWebApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techls.fdoClientsManagerWebApp.entities.CashboxModel;

@Repository
public interface CashboxModelRepository extends JpaRepository<CashboxModel, Long> {
	public CashboxModel findById(long id);
	public List<CashboxModel> findAll();
}
