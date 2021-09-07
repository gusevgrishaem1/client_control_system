package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperator;

public interface FiscalDataOperatorService {
	public FiscalDataOperator getById(long id);
	public List<FiscalDataOperator> getAll();
	public void deleteById(long id);
	public FiscalDataOperator save(FiscalDataOperator fiscalDataOperator);
}
