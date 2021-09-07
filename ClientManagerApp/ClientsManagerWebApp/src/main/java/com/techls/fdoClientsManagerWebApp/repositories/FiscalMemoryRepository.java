package com.techls.fdoClientsManagerWebApp.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.techls.fdoClientsManagerWebApp.entities.FiscalMemory;

@Repository
public interface FiscalMemoryRepository extends JpaRepository<FiscalMemory, Long> {
	public FiscalMemory findById(long idCashbox);
	public List<FiscalMemory> findByDeactivationDateLessThan(LocalDate date, Sort sort);
	@Query("SELECT f FROM FiscalMemory f WHERE f.cashbox.client.name LIKE %:clientName% ORDER BY deactivationDate")
	public List<FiscalMemory> findByClientName(@Param("clientName") String clientName);
}
