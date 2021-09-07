package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.CashboxModel;
import com.techls.fdoClientsManagerWebApp.repositories.CashboxModelRepository;

@Service
public class CashboxModelServiceImpl implements CashboxModelService {

	@Autowired
	CashboxModelRepository cashboxModelRepository;
	
	@Override
	public CashboxModel getById(long id) {
		return cashboxModelRepository.findById(id);
	}

	@Override
	public List<CashboxModel> getAll() {
		return cashboxModelRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		cashboxModelRepository.deleteById(id);		
	}

	@Override
	public CashboxModel save(CashboxModel model) {
		return cashboxModelRepository.save(model);
	}
}
