package com.techls.fdoClientsManagerWebApp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FiscalDataOperator {
	
	@Id
    @GeneratedValue
    private long id;
	
	private String name;
	
	private String individualTaxpayerNumber;
	
	@OneToMany(mappedBy = "fiscalDataOperator", orphanRemoval = true)
    private List<FiscalDataOperatorAgreement> fiscalDataOperatorAgreements = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndividualTaxpayerNumber() {
		return individualTaxpayerNumber;
	}

	public void setIndividualTaxpayerNumber(String individualTaxpayerNumber) {
		this.individualTaxpayerNumber = individualTaxpayerNumber;
	}

	public List<FiscalDataOperatorAgreement> getFiscalDataOperatorAgreements() {
		return fiscalDataOperatorAgreements;
	}

	public void setFiscalDataOperatorAgreements(List<FiscalDataOperatorAgreement> fiscalDataOperatorAgreements) {
		this.fiscalDataOperatorAgreements = fiscalDataOperatorAgreements;
	}
	
	public void deleteFiscalDataOperatorAgreement(FiscalDataOperatorAgreement fiscalDataOperatorAgreement) {
		this.fiscalDataOperatorAgreements.remove(fiscalDataOperatorAgreement);
		fiscalDataOperatorAgreement.setFiscalDataOperator(null);
	}
	
	public void addFiscalDataOperatorAgreement(FiscalDataOperatorAgreement fiscalDataOperatorAgreement) {
		this.fiscalDataOperatorAgreements.add(fiscalDataOperatorAgreement);
		fiscalDataOperatorAgreement.setFiscalDataOperator(this);
	}
	
	
}
