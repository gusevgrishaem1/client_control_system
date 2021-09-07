package com.techls.fdoClientsManagerWebApp.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.entities.Firmware;
import com.techls.fdoClientsManagerWebApp.repositories.FirmwareRepository;

@Service
public class FirmwareServiceImpl implements FirmwareService {
	
	@Autowired
	private FirmwareRepository firmwareRepository;
	
	@Autowired
	private CashboxService cashboxService;
	
	@Override
	public Firmware getById(long idCashbox) {
		return firmwareRepository.findById(idCashbox);

	}

	@Override
	public Firmware add(long idCashbox, Firmware firmware) {
		Cashbox cashbox = cashboxService.getById(idCashbox);
		firmware.setCashbox(cashbox);
		firmware.setInstallationDate(LocalDate.now());
		return firmwareRepository.save(firmware);
	}

	@Override
	public Firmware update(Firmware firmware) {
		return firmwareRepository.save(firmware);
	}	
	
}
