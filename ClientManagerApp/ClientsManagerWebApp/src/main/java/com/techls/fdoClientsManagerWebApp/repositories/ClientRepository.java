package com.techls.fdoClientsManagerWebApp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techls.fdoClientsManagerWebApp.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	public List<Client> findAll();
	public List<Client> findByNameContaining(String name);
	public Client findById(long id);
}
