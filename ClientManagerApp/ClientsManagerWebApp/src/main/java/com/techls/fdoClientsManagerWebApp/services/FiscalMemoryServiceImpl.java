package com.techls.fdoClientsManagerWebApp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.entities.FiscalMemory;
import com.techls.fdoClientsManagerWebApp.repositories.FiscalMemoryRepository;

@Service
public class FiscalMemoryServiceImpl implements FiscalMemoryService {

	@Autowired
	FiscalMemoryRepository fiscalMemoryRepository;
	
	@Autowired
	private CashboxService cashboxService;
	
	@Override
	public FiscalMemory getById(long idCashbox) {
		return fiscalMemoryRepository.findById(idCashbox);
	}

	@Override
	public FiscalMemory update(FiscalMemory fiscalMemory) {
		fiscalMemory.setDeactivationDate(fiscalMemory.getActivationDate().plusDays(fiscalMemory.getValidity()));
		return fiscalMemoryRepository.save(fiscalMemory);
	}

	@Override
	public FiscalMemory add(long idCashbox, FiscalMemory fiscalMemory) {
		Cashbox cashbox = cashboxService.getById(idCashbox);
		fiscalMemory.setCashbox(cashbox);
		fiscalMemory.setDeactivationDate(fiscalMemory.getActivationDate().plusDays(fiscalMemory.getValidity()));
		return fiscalMemoryRepository.save(fiscalMemory);
	}
	
	@Override
	public List<FiscalMemory> getAll() {
		return fiscalMemoryRepository.findAll(Sort.by(Sort.Direction.ASC, "deactivationDate"));
	}
	
	@Override
	public List<FiscalMemory> getByDeactivationDateLessThan(LocalDate date) {
		return fiscalMemoryRepository.findByDeactivationDateLessThan(date,Sort.by(Sort.Direction.ASC, "deactivationDate"));
	}
	
	@Override
	public List<FiscalMemory> getByClientName(String clientName) {
		return fiscalMemoryRepository.findByClientName(clientName);
	}

}
