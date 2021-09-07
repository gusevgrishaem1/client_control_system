package com.techls.fdoClientsManagerWebApp.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techls.fdoClientsManagerWebApp.entities.FiscalMemory;
import com.techls.fdoClientsManagerWebApp.services.FiscalMemoryService;

@Controller 
public class FiscalMemoryController {
	
	@Autowired
	private FiscalMemoryService fiscalMemoryService;
	
	@GetMapping("/fiscalMemories")
	public String getAll(Model model) {
		model.addAttribute("listFiscalMemories", fiscalMemoryService.getAll());
		model.addAttribute("clientName", new String());
		return "list_fiscal_memory";
	}
	
	@GetMapping("/fiscalMemories/oneMonth")
	public String getByDeactivationDateOneMonth(Model model) {
		model.addAttribute("listFiscalMemories", fiscalMemoryService.getByDeactivationDateLessThan(LocalDate.now().plusDays(30)));
		model.addAttribute("clientName", new String());
		return "list_fiscal_memory";
	}
	
	@GetMapping("/fiscalMemories/twoMonths")
	public String getByDeactivationDateTwoMonths(Model model) {
		model.addAttribute("listFiscalMemories", fiscalMemoryService.getByDeactivationDateLessThan(LocalDate.now().plusDays(60)));
		model.addAttribute("clientName", new String());
		return "list_fiscal_memory";
	}
	
	@PostMapping("/fiscalMemories/byClientName")
	public String getByClientName(@ModelAttribute("clientName") String clientName, Model model) {
		model.addAttribute("listFiscalMemories", fiscalMemoryService.getByClientName(clientName));
		model.addAttribute("clientName", clientName);
		return "list_fiscal_memory";
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/fiscalMemoryAddition")
	public String showAddForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		FiscalMemory fiscalMemory = new FiscalMemory();
		model.addAttribute("fiscalMemory", fiscalMemory);
		return "fiscal_memory_edit_form";
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/fiscalMemoryEditor")
	public String showUpdateForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		FiscalMemory fiscalMemory = fiscalMemoryService.getById(idCashbox);
		model.addAttribute("fiscalMemory", fiscalMemory);
		return "fiscal_memory_edit_form";
	}
	
	@PostMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/saveFiscalMemory")
	public String save(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, @ModelAttribute("fiscalMemory") FiscalMemory fiscalMemory) {
		if(fiscalMemory.getCashbox()==null)
		fiscalMemoryService.add(idCashbox, fiscalMemory);
		else fiscalMemoryService.update(fiscalMemory);
		return "redirect:/clients/clientEditor/" + idClient + "/cashboxEditor/" + idCashbox;
	}
	
	
}
