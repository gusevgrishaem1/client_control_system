package com.techls.fdoClientsManagerWebApp.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.CascadeType;


@Entity
public class Cashbox {
	
	@Id
    @GeneratedValue
    private long id;
	
    private String factoryNumber;
	
    private String installationAddress;
	
    private String registrationNumber;

	@ManyToOne(fetch = FetchType.LAZY)
    private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private CashboxModel cashboxModel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private TaxationSystem taxationSystem;
	
	@OneToOne(mappedBy = "cashbox", orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private FiscalDataOperatorAgreement fiscalDataOperatorAgreement;
	
	@OneToOne(mappedBy = "cashbox", orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private FiscalMemory fiscalMemory;
	
	@OneToOne(mappedBy = "cashbox", orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private Firmware firmware;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFactoryNumber() {
		return factoryNumber;
	}

	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}

	public String getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(String installationAddress) {
		this.installationAddress = installationAddress;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public CashboxModel getCashboxModel() {
		return cashboxModel;
	}

	public void setCashboxModel(CashboxModel model) {
		this.cashboxModel = model;
	}

	public TaxationSystem getTaxationSystem() {
		return taxationSystem;
	}

	public void setTaxationSystem(TaxationSystem taxationSystem) {
		this.taxationSystem = taxationSystem;
	}
	
	
}
