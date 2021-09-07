package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.CashboxModel;

public interface CashboxModelService {
	public CashboxModel getById(long id);
	public List<CashboxModel> getAll();
	public void deleteById(long id);
	public CashboxModel save(CashboxModel model);
}
