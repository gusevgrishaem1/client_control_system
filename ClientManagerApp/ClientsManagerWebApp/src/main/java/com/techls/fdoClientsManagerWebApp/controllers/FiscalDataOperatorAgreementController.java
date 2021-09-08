package com.techls.fdoClientsManagerWebApp.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperatorAgreement;
import com.techls.fdoClientsManagerWebApp.services.CashboxService;
import com.techls.fdoClientsManagerWebApp.services.FiscalDataOperatorAgreementService;
import com.techls.fdoClientsManagerWebApp.services.FiscalDataOperatorService;

@Controller
public class FiscalDataOperatorAgreementController {
	
	//test
	@Autowired
	private CashboxService cashboxService;
	
	@Autowired
	private FiscalDataOperatorAgreementService fiscalDataOperatorAgreementService;
	
	@Autowired
	private FiscalDataOperatorService fiscalDataOperatorService;
	
	//test
	@GetMapping("/fiscalDataOperatorAgreements")
	public String getAll(Model model) {
		//test
		model.addAttribute("listFiscalDataOperatorAgreements", cashboxService.getAll());
		model.addAttribute("clientName", new String());
		model.addAttribute("msg", new String("Без передачи"));
		return "list_fiscal_data_operator_agreement";
	}
	
	//test
	@GetMapping("/fiscalDataOperatorAgreements/oneMonth")
	public String getByDeactivationDateOneMonth(Model model) {
		model.addAttribute("listFiscalDataOperatorAgreements", cashboxService.getAllCashboxWithFiscalDataOperatorAgreementDeactivationDateLessThan(LocalDate.now().plusDays(30)));
		model.addAttribute("clientName", new String());
		return "list_fiscal_data_operator_agreement";
	}
	
	//test
	@GetMapping("/fiscalDataOperatorAgreements/twoMonths")
	public String getByDeactivationDateTwoMonths(Model model) {
		model.addAttribute("listFiscalDataOperatorAgreements", fiscalDataOperatorAgreementService.getByDeactivationDateLessThan(LocalDate.now().plusDays(60)));
		model.addAttribute("clientName", new String());
		return "list_fiscal_data_operator_agreement";
	}
	
	//test
	@PostMapping("/fiscalDataOperatorAgreements/byClientName")
	public String getByClientName(@ModelAttribute("clientName") String clientName, Model model) {
		model.addAttribute("listFiscalDataOperatorAgreements", fiscalDataOperatorAgreementService.getByClientName(clientName));
		model.addAttribute("clientName", clientName);
		return "list_fiscal_data_operator_agreement";
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/fiscalDataOperatorAgreementAddition")
	public String showAddForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		FiscalDataOperatorAgreement fiscalDataOperatorAgreement= new FiscalDataOperatorAgreement();
		model.addAttribute("fiscalDataOperatorAgreement",fiscalDataOperatorAgreement);
		model.addAttribute("listFiscalDataOperators", fiscalDataOperatorService.getAll());
		return "fiscal_data_operator_agreement_edit_form";
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/fiscalDataOperatorAgreementEditor")
	public String showUpdateForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		FiscalDataOperatorAgreement fiscalDataOperatorAgreement = fiscalDataOperatorAgreementService.getById(idCashbox);
		model.addAttribute("fiscalDataOperatorAgreement",fiscalDataOperatorAgreement);
		model.addAttribute("listFiscalDataOperators", fiscalDataOperatorService.getAll());
		return "fiscal_data_operator_agreement_edit_form";
	}
	
	@PostMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/saveFiscalDataOperatorAgreement")
	public String save(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, @ModelAttribute("fiscalDataOperatorAgreement") FiscalDataOperatorAgreement fiscalDataOperatorAgreement) {
		if (fiscalDataOperatorAgreement.getCashbox()==null) 
		fiscalDataOperatorAgreementService.add(idCashbox, fiscalDataOperatorAgreement);
		else fiscalDataOperatorAgreementService.update(fiscalDataOperatorAgreement);
		return "redirect:/clients/clientEditor/" + idClient + "/cashboxEditor/" + idCashbox;
	}
	
}
