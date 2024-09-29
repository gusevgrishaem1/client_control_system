package com.techls.ofd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "cash_register_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Касса клиента
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cash_register_seq")
    @SequenceGenerator(name = "cash_register_seq", sequenceName = "cash_register_data_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    // Заводской номер
    @Column(name = "nn_factory")
    private String factoryNumber;

    @Column(name = "nm_install_address")
    private String installationAddress;

    @Column(name = "nn_registration")
    private String registrationNumber;

    @Column(name = "pr_archive")
    private Boolean archive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_model")
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_taxation_system")
    private TaxationSystem taxationSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ofd")
    private OFD ofd;

    // ОФД договор
    @Column(name = "ofd_contract_activation_date")
    private LocalDate activationDate;

    @Column(name = "ofd_contract_deactivation_date")
    private LocalDate deactivationDate;

    // Фискальный апарат
    @Column(name = "fiscal_type")
    private String fiscalType;

    @Column(name = "fiscal_nn_factory")
    private String fiscalFactoryNumber;

    @Column(name = "fiscal_activation_date")
    private LocalDate fiscalActivationDate;

    @Column(name = "fiscal_deactivation_date")
    private LocalDate fiscalDeactivationDate;

    // Прошивка
    @Column(name = "os_nm_version")
    private String version;

    @Column(name = "os_install_date")
    private LocalDate installationDate;
}

