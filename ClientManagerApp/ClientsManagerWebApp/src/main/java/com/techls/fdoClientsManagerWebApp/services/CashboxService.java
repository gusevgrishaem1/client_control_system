package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.Cashbox;

public interface CashboxService {
	
	public Cashbox add(long idClient, Cashbox cashbox);
	
	public Cashbox update(Cashbox cashbox);
	
	public void deleteById(long idCashbox);
	
	public Cashbox getById(long idCashbox);
	
	public List<Cashbox> getAll();
	
	public List<Cashbox> getAllByIdClient(long idClient);
}
