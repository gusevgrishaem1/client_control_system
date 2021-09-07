package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.TaxationSystem;
import com.techls.fdoClientsManagerWebApp.repositories.TaxationSystemRepository;

@Service
public class TaxationSystemServiceImpl implements TaxationSystemService {
	
	@Autowired
	TaxationSystemRepository taxationSystemRepository; 
	
	@Override
	public TaxationSystem getById(long id) {
		return taxationSystemRepository.findById(id);
	}

	@Override
	public List<TaxationSystem> getAll() {
		return taxationSystemRepository.findAll();
	}

	@Override
	public void deleteById(long id) {
		taxationSystemRepository.deleteById(id);
	}

	@Override
	public TaxationSystem save(TaxationSystem taxationSystem) {
		return taxationSystemRepository.save(taxationSystem);
	}

}
