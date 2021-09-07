package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.Client;
import com.techls.fdoClientsManagerWebApp.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void deleteById(long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Client getById(long id) {
		return clientRepository.findById(id);
	}

	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public List<Client> getByName(String name) {
		return clientRepository.findByNameContaining(name);
	}

}
