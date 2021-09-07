package com.techls.fdoClientsManagerWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techls.fdoClientsManagerWebApp.entities.Firmware;
import com.techls.fdoClientsManagerWebApp.services.FirmwareService;

@Controller
public class FirmwareController {
	
	@Autowired
	private FirmwareService firmwareService;
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/firmwareAddition")
	public String showAddForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		Firmware firmware= new Firmware();
		model.addAttribute("firmware", firmware);
		return "firmware_edit_form";
	}
	
	@GetMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/firmwareEditor")
	public String showUpdateForm(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, Model model) {
		Firmware firmware= firmwareService.getById(idCashbox);
		model.addAttribute("firmware", firmware);
		return "firmware_edit_form";
	}
	
	@PostMapping("/clients/clientEditor/{idClient}/cashboxEditor/{idCashbox}/saveFirmware")
	public String update(@PathVariable(value = "idCashbox") long idCashbox, @PathVariable(value = "idClient") long idClient, @ModelAttribute("firmware") Firmware firmware) {
		if (firmware.getCashbox()==null) firmwareService.add(idCashbox, firmware);
		else firmwareService.update(firmware);
		return "redirect:/clients/clientEditor/" + idClient + "/cashboxEditor/" + idCashbox;
	}
}
