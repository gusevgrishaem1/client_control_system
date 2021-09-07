package com.techls.fdoClientsManagerWebApp.services;

import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.Client;

public interface ClientService {
	
	public Client save(Client client);
	
	public void deleteById(long id);
	
	public Client getById(long id);
	
	public List<Client> getAll();
	
	public List<Client> getByName(String name);
		
}
