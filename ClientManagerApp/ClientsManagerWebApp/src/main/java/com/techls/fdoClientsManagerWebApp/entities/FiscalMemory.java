package com.techls.fdoClientsManagerWebApp.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class FiscalMemory {
	
    @Id
    private Long id;

    private String type;
    
    private String factoryNumber;

    private int validity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deactivationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Cashbox cashbox;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFactoryNumber() {
		return factoryNumber;
	}

	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
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

	public Cashbox getCashbox() {
		return cashbox;
	}

	public void setCashbox(Cashbox cashbox) {
		this.cashbox = cashbox;
	}
        
}
