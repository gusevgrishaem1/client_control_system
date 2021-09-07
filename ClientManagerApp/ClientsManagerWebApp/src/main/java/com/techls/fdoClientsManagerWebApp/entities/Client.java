package com.techls.fdoClientsManagerWebApp.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@Id
    @GeneratedValue
    private long id;

    private String individualTaxpayerNumber;
	
    private String name;
	
    private String telephone;
	
	@OneToMany(mappedBy = "client", orphanRemoval = true)
    private List<Cashbox> cashboxs = new ArrayList<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIndividualTaxpayerNumber() {
		return individualTaxpayerNumber;
	}

	public void setIndividualTaxpayerNumber(String individualTaxpayerNumber) {
		this.individualTaxpayerNumber = individualTaxpayerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Cashbox> getCashboxs() {
		return cashboxs;
	}

	public void setCashboxs(List<Cashbox> cashboxs) {
		this.cashboxs = cashboxs;
	}
	
	public void deleteCashbox(Cashbox cashbox) {
		this.cashboxs.remove(cashbox);
		cashbox.setClient(null);
	}
	
	public void addCashbox(Cashbox cashbox) {
		this.cashboxs.add(cashbox);
		cashbox.setClient(this);
	}
}
