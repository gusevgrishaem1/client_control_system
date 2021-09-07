package com.techls.fdoClientsManagerWebApp.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;
import com.techls.fdoClientsManagerWebApp.repositories.FiscalDataOperatorAgreementRepository;

@Service
public class FiscalDataOperatorAgreementServiceImpl implements FiscalDataOperatorAgreementService {
	
	@Autowired
	private FiscalDataOperatorAgreementRepository  fiscalDataOperatorAgreementRepository; 
	
	@Autowired
	private CashboxService cashboxService;
	
	@Override
	public FiscalDataOperatorAgreement getById(long idCashbox) {
		return fiscalDataOperatorAgreementRepository.findById(idCashbox);
	}

	@Override
	public FiscalDataOperatorAgreement add(long idCashbox, FiscalDataOperatorAgreement fiscalDataOperatorAgreement) {
		Cashbox cashbox = cashboxService.getById(idCashbox);
		fiscalDataOperatorAgreement.setCashbox(cashbox);
		fiscalDataOperatorAgreement.setDeactivationDate(fiscalDataOperatorAgreement.getActivationDate().plusDays(fiscalDataOperatorAgreement.getValidity()));
		return fiscalDataOperatorAgreementRepository.save(fiscalDataOperatorAgreement);
	}

	@Override
	public FiscalDataOperatorAgreement update(FiscalDataOperatorAgreement fiscalDataOperatorAgreement) {
		fiscalDataOperatorAgreement.setDeactivationDate(fiscalDataOperatorAgreement.getActivationDate().plusDays(fiscalDataOperatorAgreement.getValidity()));
		return fiscalDataOperatorAgreementRepository.save(fiscalDataOperatorAgreement);
	}
	
	@Override
	public List<FiscalDataOperatorAgreement> getAll() {
		return fiscalDataOperatorAgreementRepository.findAll(Sort.by(Sort.Direction.ASC, "deactivationDate"));
	}

	@Override
	public List<FiscalDataOperatorAgreement> getByDeactivationDateLessThan(LocalDate date) {
		return fiscalDataOperatorAgreementRepository.findByDeactivationDateLessThan(date, Sort.by(Sort.Direction.ASC, "deactivationDate"));
	}
	
	@Override
	public List<FiscalDataOperatorAgreement> getByClientName(String clientName) {
		return fiscalDataOperatorAgreementRepository.findByClientName(clientName);
	}


}
