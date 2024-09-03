package com.techls.ofd.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CashRegisterDTO {
    private Long id;
    private String factoryNumber;
    private String installationAddress;
    private String registrationNumber;
    // Архивность
    private Boolean archive;
    // Клиент
    private Long clientId;
    private String clientTitle;
    // Модель
    private Long modelId;
    private String modelTitle;
    // СНО
    private Long taxationSystemId;
    private String taxationSystemTitle;
    // ОФД
    private Long ofdId;
    private String ofdTitle;
    private LocalDate activationDate;
    private LocalDate deactivationDate;
    // ФН
    private String fiscalType;
    private String fiscalFactoryNumber;
    private LocalDate fiscalActivationDate;
    private LocalDate fiscalDeactivationDate;
    // Прошивка
    private String version;
    private LocalDate installationDate;
}
