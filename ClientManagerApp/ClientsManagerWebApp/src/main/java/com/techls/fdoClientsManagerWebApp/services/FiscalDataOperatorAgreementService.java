package com.techls.fdoClientsManagerWebApp.services;

import java.time.LocalDate;
import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;

public interface FiscalDataOperatorAgreementService {
	public FiscalDataOperatorAgreement getById(long idCashbox);
	public FiscalDataOperatorAgreement add(long idCashbox, FiscalDataOperatorAgreement fiscalDataOperator);
	public FiscalDataOperatorAgreement update(FiscalDataOperatorAgreement fiscalDataOperator);
	public List<FiscalDataOperatorAgreement> getAll();
	public List<FiscalDataOperatorAgreement> getByDeactivationDateLessThan(LocalDate date);
	public List<FiscalDataOperatorAgreement> getByClientName(String clientName);
}
