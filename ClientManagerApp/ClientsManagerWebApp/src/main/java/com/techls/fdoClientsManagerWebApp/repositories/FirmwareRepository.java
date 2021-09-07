package com.techls.fdoClientsManagerWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techls.fdoClientsManagerWebApp.entities.Firmware;

@Repository
public interface FirmwareRepository extends JpaRepository<Firmware, Long> {
	public Firmware findById(long id);
}
