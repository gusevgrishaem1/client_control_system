package com.techls.fdoClientsManagerWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techls.fdoClientsManagerWebApp.entities.Cashbox;
import com.techls.fdoClientsManagerWebApp.entities.Firmware;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;
import com.techls.fdoClientsManagerWebApp.entities.FiscalMemory;
import com.techls.fdoClientsManagerWebApp.services.CashboxModelService;
import com.techls.fdoClientsManagerWebApp.services.CashboxService;
import com.techls.fdoClientsManagerWebApp.services.FirmwareService;
import com.techls.fdoClientsManagerWebApp.services.FiscalDataOperatorAgreementService;
import com.techls.fdoClientsManagerWebApp.services.FiscalDataOperatorService;
import com.techls.fdoClientsManagerWebApp.services.FiscalMemoryService;
import com.techls.fdoClientsManagerWebApp.services.TaxationSystemService;

@Controller
public class CashboxController {
	
	@Autowired
	private CashboxService cashboxService;
	
	@Autowired
	private FirmwareService firmwareService;
	
	@Autowired
	private FiscalMemoryService fiscalMemoryService;
	
	@Autowired
	private FiscalDataOperatorAgreementService fiscalDataOperatorAgreementService;
	
	@Autowired
	private FiscalDataOperatorService fiscalDataOperatorService;
	
	@Autowired
	private TaxationSystemService taxationSystemService;
	
	@Autowired
	private CashboxModelService cashboxModelService;
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxAddition")
	public String showAddForm(@PathVariable("idClient") long idClient, Model model) {
		Cashbox cashbox = new Cashbox();
		model.addAttribute("cashbox", cashbox);
		model.addAttribute("idClient", idClient);
		model.addAttribute("listTaxationSystems",taxationSystemService.getAll());
		model.addAttribute("listCashboxModels",cashboxModelService.getAll());
		return "cashbox_add_form";
	}
	
	@PostMapping("/clients/clientEditor/{idClient}/cashboxAddition/saveCashbox")
	public String add(@ModelAttribute("cashbox") Cashbox cashbox, @PathVariable(value = "idClient") long idClient) {
		cashboxService.add(idClient, cashbox);
		return "redirect:/clients/clientEditor/" + idClient;
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/deleteCashbox/{idCashbox}")
	public String delete(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient) {
		cashboxService.deleteById(idCashbox);
		return "redirect:/clients/clientEditor/"+idClient;
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}")
	public String showUpdateForm(@PathVariable(value = "idClient") long idClient, @PathVariable(value = "idCashbox") long idCashbox, Model model) {
		Cashbox cashbox = cashboxService.getById(idCashbox);
		FiscalDataOperatorAgreement fiscalDataOperatorAgreement = fiscalDataOperatorAgreementService.getById(idCashbox);
		Firmware firmware = firmwareService.getById(idCashbox);
		FiscalMemory fiscalMemory = fiscalMemoryService.getById(idCashbox);
		model.addAttribute("fiscalDataOperatorAgreement", fiscalDataOperatorAgreement);
		model.addAttribute("fiscalMemory", fiscalMemory);
		model.addAttribute("firmware", firmware);
		model.addAttribute("cashbox", cashbox);
		model.addAttribute("idClient", idClient);
		model.addAttribute("listTaxationSystems",taxationSystemService.getAll());
		model.addAttribute("listCashboxModels",cashboxModelService.getAll());
		model.addAttribute("listFiscalDataOperators",fiscalDataOperatorService.getAll());
		if(fiscalDataOperatorService.getAll().isEmpty()) {
			String error = "Заполните справочники!";
			model.addAttribute("error", error);
		}
		return "cashbox_editor_form";
	}
	
	@PostMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/saveCashbox")
	public String update(@ModelAttribute("cashbox") Cashbox cashbox, @PathVariable(value = "idClient") long idClient) {
		cashboxService.update(cashbox);
		return "redirect:/clients/clientEditor/" + idClient;
	}
	
	
}
