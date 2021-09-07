package com.techls.fdoClientsManagerWebApp.entities;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class FiscalDataOperatorAgreement {

    @Id
    private Long id;

    private int validity;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deactivationDate;
    
	@ManyToOne(fetch = FetchType.LAZY)
    private FiscalDataOperator fiscalDataOperator;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Cashbox cashbox;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public LocalDate getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(LocalDate activationDate) {
		this.activationDate = activationDate;
	}

	public LocalDate getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(LocalDate deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public FiscalDataOperator getFiscalDataOperator() {
		return fiscalDataOperator;
	}

	public void setFiscalDataOperator(FiscalDataOperator fiscalDataOperator) {
		this.fiscalDataOperator = fiscalDataOperator;
	}

	public Cashbox getCashbox() {
		return cashbox;
	}

	public void setCashbox(Cashbox cashbox) {
		this.cashbox = cashbox;
	}
    
}
