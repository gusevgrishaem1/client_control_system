package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.TaxationSystem;

public interface TaxationSystemService {
	public TaxationSystem getById(long id);
	public List<TaxationSystem> getAll();
	public void deleteById(long id);
	public TaxationSystem save(TaxationSystem taxationSystem);
}
