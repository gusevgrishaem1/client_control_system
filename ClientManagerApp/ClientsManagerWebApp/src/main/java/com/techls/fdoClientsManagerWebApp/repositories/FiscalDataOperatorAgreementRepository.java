package com.techls.fdoClientsManagerWebApp.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;

@Repository
public interface FiscalDataOperatorAgreementRepository extends JpaRepository<FiscalDataOperatorAgreement, Long> {
	public FiscalDataOperatorAgreement findById(long idCashbox);
	public List<FiscalDataOperatorAgreement> findByDeactivationDateLessThan(LocalDate date, Sort sort);
	@Query("SELECT f FROM FiscalDataOperatorAgreement f WHERE f.cashbox.client.name LIKE %:clientName% ORDER BY deactivationDate")
	public List<FiscalDataOperatorAgreement> findByClientName(@Param("clientName") String clientName);
}

 