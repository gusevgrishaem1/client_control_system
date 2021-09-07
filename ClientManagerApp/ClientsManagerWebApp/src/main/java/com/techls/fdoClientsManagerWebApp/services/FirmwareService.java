package com.techls.fdoClientsManagerWebApp.services;

import com.techls.fdoClientsManagerWebApp.entities.Firmware;

public interface FirmwareService {
	
	public Firmware getById(long idCashbox);
	
	public Firmware add(long idCashbox, Firmware firmware);
	
	public Firmware update(Firmware firmware);
}
