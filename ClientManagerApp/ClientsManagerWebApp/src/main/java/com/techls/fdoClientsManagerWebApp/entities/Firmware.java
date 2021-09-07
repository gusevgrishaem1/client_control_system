package com.techls.fdoClientsManagerWebApp.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Firmware {
	
	@Id
    private Long id;

    private String version;

	@Column(name = "installationDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate installationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Cashbox cashbox;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public LocalDate getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(LocalDate installationDate) {
		this.installationDate = installationDate;
	}

	public Cashbox getCashbox() {
		return cashbox;
	}

	public void setCashbox(Cashbox cashbox) {
		this.cashbox = cashbox;
	}

}
