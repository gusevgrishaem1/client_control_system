package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperator;
import com.techls.fdoClientsManagerWebApp.repositories.FiscalDataOperatorRepository;

@Service
public class FiscalDataOperatorServiceImpl implements FiscalDataOperatorService {

	@Autowired
	FiscalDataOperatorRepository fiscalDataOperatorRepository; 
	
	@Override
	public FiscalDataOperator getById(long id) {
		return fiscalDataOperatorRepository.findById(id);
	}

	@Override
	public List<FiscalDataOperator> getAll() {
		return fiscalDataOperatorRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		fiscalDataOperatorRepository.deleteById(id);
	}

	@Override
	public FiscalDataOperator save(FiscalDataOperator fiscalDataOperator) {
		return fiscalDataOperatorRepository.save(fiscalDataOperator);
	}
}
