package com.techls.fdoClientsManagerWebApp.services;

import java.time.LocalDate;
import java.util.List;

import com.techls.fdoClientsManagerWebApp.entities.FiscalMemory;

public interface FiscalMemoryService {
	public FiscalMemory getById(long idCashbox);
	public FiscalMemory update(FiscalMemory fiscalAccumulator);
	public FiscalMemory add(long idCashbox, FiscalMemory fiscalAccumulator);
	public List<FiscalMemory> getAll();
	public List<FiscalMemory> getByDeactivationDateLessThan(LocalDate date);
	public List<FiscalMemory> getByClientName(String name);
}
