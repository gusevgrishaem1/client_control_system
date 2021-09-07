package com.techls.fdoClientsManagerWebApp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CashboxModel {
	
	@Id
    @GeneratedValue
    private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "cashboxModel", orphanRemoval = true)
    private List<Cashbox> cashboxs = new ArrayList<>();

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

	public List<Cashbox> getCashboxs() {
		return cashboxs;
	}

	public void setCashboxs(List<Cashbox> cashboxs) {
		this.cashboxs = cashboxs;
	}
	
	public void deleteCashbox(Cashbox cashbox) {
		this.cashboxs.remove(cashbox);
		cashbox.setCashboxModel(null);
	}
	
	public void addCashbox(Cashbox cashbox) {
		this.cashboxs.add(cashbox);
		cashbox.setCashboxModel(this);
	}
	
}
