package com.techls.fdoClientsManagerWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.techls.fdoClientsManagerWebApp.entities.CashboxModel;
import com.techls.fdoClientsManagerWebApp.entities.FiscalDataOperator;
import com.techls.fdoClientsManagerWebApp.entities.TaxationSystem;
import com.techls.fdoClientsManagerWebApp.services.CashboxModelService;
import com.techls.fdoClientsManagerWebApp.services.FiscalDataOperatorService;
import com.techls.fdoClientsManagerWebApp.services.TaxationSystemService;

@Controller
public class DirectoryController {
	
	@Autowired
	private FiscalDataOperatorService fiscalDataOperatorService;
	
	@Autowired
	private TaxationSystemService taxationSystemService;
	
	@Autowired
	private CashboxModelService cashboxModelService;
	
	@GetMapping("/directories/deleteTaxationSystem/{idTaxationSystem}")
	public String deleteTaxationSystem(@PathVariable("idTaxationSystem") long idTaxationSystem) {
		taxationSystemService.deleteById(idTaxationSystem);
		return "redirect:/directories";
	}
	
	@GetMapping("/directories/deleteCashboxModel/{idCashboxModel}")
	public String deleteCashboxModel(@PathVariable("idCashboxModel") long idCashboxModel) {
		cashboxModelService.deleteById(idCashboxModel);
		return "redirect:/directories";
	}
	
	@GetMapping("/directories/deleteFiscalDataOperator/{idFiscalDataOperator}")
	public String deleteFiscalDataOperator(@PathVariable("idFiscalDataOperator") long idFiscalDataOperator) {
		fiscalDataOperatorService.deleteById(idFiscalDataOperator);
		return "redirect:/directories";
	}
	
	@PostMapping("/directories/saveTaxationSystem")
	public String saveTaxationSystem(@ModelAttribute("taxationSystem") TaxationSystem taxationSystem) {
		taxationSystemService.save(taxationSystem);
		return "redirect:/directories";
	}
	
	@PostMapping("/directories/saveCashboxModel")
	public String saveCashboxModel(@ModelAttribute("cashboxModel") CashboxModel cashboxModel) {
		cashboxModelService.save(cashboxModel);
		return "redirect:/directories";
	}
	
	@PostMapping("/directories/saveFiscalDataOperator")
	public String saveFiscalDataOperator(@ModelAttribute("fiscalDataOperator") FiscalDataOperator fiscalDataOperator) {
		fiscalDataOperatorService.save(fiscalDataOperator);
		return "redirect:/directories";
	}
	
	@GetMapping("/directories/taxationSystemAddition")
	public String showAddFormTaxationSystem(Model model) {
		TaxationSystem taxationSystem = new TaxationSystem();
		model.addAttribute("taxationSystem", taxationSystem);
		return "taxation_system_edit_form";
	}
	
	@GetMapping("/directories/cashboxModelAddition")
	public String showAddFormCashboxModel(Model model) {
		CashboxModel cashboxModel = new CashboxModel();
		model.addAttribute("cashboxModel", cashboxModel);
		return "cashbox_model_edit_form";
	}
	
	@GetMapping("/directories/fiscalDataOperatorAddition")
	public String showAddFormFiscalDataOperator(Model model) {
		FiscalDataOperator fiscalDataOperator = new FiscalDataOperator();
		model.addAttribute("fiscalDataOperator", fiscalDataOperator);
		return "fiscal_data_operator_edit_form";
	}
	
	
	@GetMapping("/directories/fiscalDataOperatorEditor/{idFiscalDataOperator}")
	public String showUpdateFormFiscalDataOperator(@PathVariable(value = "idFiscalDataOperator") long idFiscalDataOperator, Model model) {
		FiscalDataOperator fiscalDataOperator = fiscalDataOperatorService.getById(idFiscalDataOperator);
		model.addAttribute("fiscalDataOperator", fiscalDataOperator);
		return "fiscal_data_operator_edit_form";
	}
	
	@GetMapping("/directories/cashboxModelEditor/{idCashboxModel}")
	public String showUpdateFormCashboxModel(@PathVariable(value = "idCashboxModel") long idCashboxModel, Model model) {
		CashboxModel cashboxModel = cashboxModelService.getById(idCashboxModel);
		model.addAttribute(cashboxModel);
		return "cashbox_model_edit_form";
	}
	
	@GetMapping("/directories/taxationSystemEditor/{idTaxationSystem}")
	public String showUpdateFormTaxationSystem(@PathVariable(value = "idTaxationSystem") long idTaxationSystem, Model model) {
		TaxationSystem taxationSystem = taxationSystemService.getById(idTaxationSystem);
		model.addAttribute("taxationSystem", taxationSystem);
		return "taxation_system_edit_form";
	}
	
	@GetMapping("/directories")
	public String getAll(Model model) {
		model.addAttribute("listFiscalDataOperators",fiscalDataOperatorService.getAll());
		model.addAttribute("listTaxationSystems",taxationSystemService.getAll());
		model.addAttribute("listCashboxModels",cashboxModelService.getAll());
		return "directories";
	}
	
}
