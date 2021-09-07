package com.techls.fdoClientsManagerWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.techls.fdoClientsManagerWebApp.entities.Client;
import com.techls.fdoClientsManagerWebApp.services.CashboxModelService;
import com.techls.fdoClientsManagerWebApp.services.CashboxService;
import com.techls.fdoClientsManagerWebApp.services.ClientService;
import com.techls.fdoClientsManagerWebApp.services.TaxationSystemService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CashboxService cashboxService;
	
	@Autowired
	private TaxationSystemService taxationSystemService;
	
	@Autowired
	private CashboxModelService cashboxModelService;
	
	@GetMapping("/clients")
	public String showAll(Model model) {
		model.addAttribute("listClients", clientService.getAll());
		return "index.html";
	}
	
	@GetMapping("/clients/clientAddition")
	public String showAddForm(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "client_add_form";
	}
	
	@GetMapping("/clients/clientEditor/{id}")
	public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
		Client client = clientService.getById(id);
		model.addAttribute("client", client);
		model.addAttribute("listCashboxs", cashboxService.getAllByIdClient(id));
		if ((taxationSystemService.getAll().isEmpty()) || (cashboxModelService.getAll().isEmpty())) {
			String error = "Заполните справочники!";
			model.addAttribute("error", error);
		}
		return "client_editor_form";
	}
	
	@PostMapping("/clients/saveClient")
	public String save(@ModelAttribute("client") Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@GetMapping("clients/deleteClient/{id}")
	public String delete(@PathVariable("id") long id) {
		clientService.deleteById(id);
		return "redirect:/clients";
	}
	
	@PostMapping("/clients/findByName")
	public String getByName(@ModelAttribute("clientName") String clientName, Model model) {
		model.addAttribute("listClients", clientService.getByName(clientName));
		model.addAttribute("clientName", clientName);
		return "index.html";
	}
	
}
