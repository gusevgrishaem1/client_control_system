package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.repositories.CashboxRepository;

@Service
public class CashboxServiceImpl implements CashboxService {
	
	
	@Autowired
	private CashboxRepository cashboxRepository;
	
	@Autowired
	private ClientService clientService;

	@Override
	public void deleteById(long id) {
		cashboxRepository.deleteById(id);
	}

	@Override
	public Cashbox getById(long id) {
		return cashboxRepository.findById(id);
	}

	@Override
	public List<Cashbox> getAll() {
		return cashboxRepository.findAll();
	}
	
	
	@Override
	public List<Cashbox> getAllByIdClient(long idClient) {
		return cashboxRepository.findByClientId(idClient);
	}

	@Override
	public Cashbox add(long idClient, Cashbox cashbox) {
		cashbox.setClient(clientService.getById(idClient));
		return cashboxRepository.save(cashbox);
	}

	@Override
	public Cashbox update(Cashbox cashbox) {
		return cashboxRepository.save(cashbox);
	}
	
	
	
}
