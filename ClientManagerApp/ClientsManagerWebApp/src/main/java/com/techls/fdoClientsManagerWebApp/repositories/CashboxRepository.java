package com.techls.fdoClientsManagerWebApp.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;

@Repository
public interface CashboxRepository extends JpaRepository<Cashbox, Long> {
	
	public List<Cashbox> findByClientId(long idClient);
	
	public List<Cashbox> findAll();
	
	public Cashbox findById(long id);
	
	public List<Cashbox> findByFiscalDataOperatorAgreementNull();
	
	@Query("SELECT c FROM Cashbox c JOIN FiscalDataOperatorAgreement f ON c.id = f.id ORDER BY f.deactivationDate")
	public List<Cashbox> findByFiscalDataOperatorAgreement();
	
	@Query("SELECT c FROM Cashbox c JOIN FiscalDataOperatorAgreement f ON c.id = f.id WHERE f.deactivationDate < :date ORDER BY f.deactivationDate")
	public List<Cashbox> findByFiscalDataOperatorAgreementDeactivationDate(@Param("date") LocalDate date);
	
	@Query("SELECT c FROM Cashbox c WHERE c.client.name LIKE %:clientName%")
	public List<FiscalDataOperatorAgreement> findByClientName(@Param("clientName") String clientName);
	
	public List<Cashbox> findByFiscalMemoryNull();
	
	@Query("SELECT c FROM Cashbox c JOIN FiscalMemory f ON c.id = f.id ORDER BY f.deactivationDate")
	public List<Cashbox> findByFiscalMemory();
	
	@Query("SELECT c FROM Cashbox c JOIN FiscalMemory f ON c.id = f.id WHERE f.deactivationDate < :date ORDER BY f.deactivationDate")
	public List<Cashbox> findByFiscalMemoryDeactivationDate(@Param("date") LocalDate date);
	
	
}
