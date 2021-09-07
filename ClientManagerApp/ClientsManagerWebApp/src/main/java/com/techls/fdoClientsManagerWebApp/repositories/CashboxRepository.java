package com.techls.fdoClientsManagerWebApp.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techls.fdoClientsManagerWebApp.entities.Cashbox;

@Repository
public interface CashboxRepository extends JpaRepository<Cashbox, Long> {
	public List<Cashbox> findByClientId(long idClient);
	public List<Cashbox> findAll();
	public Cashbox findById(long id);
}
