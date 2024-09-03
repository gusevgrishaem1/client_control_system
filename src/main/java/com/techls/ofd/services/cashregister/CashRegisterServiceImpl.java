package com.techls.ofd.services.cashregister;

import com.techls.ofd.dto.CashRegisterDTO;
import com.techls.ofd.entities.CashRegister;
import com.techls.ofd.repositories.cashregister.CashRegisterRepository;
import com.techls.ofd.repositories.client.ClientRepository;
import com.techls.ofd.repositories.model.ModelRepository;
import com.techls.ofd.repositories.ofd.OFDRepository;
import com.techls.ofd.repositories.taxationsystem.TaxationSystemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashRegisterServiceImpl implements CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;
    private final ClientRepository clientRepository;
    private final ModelRepository modelRepository;
    private final TaxationSystemRepository taxationSystemRepository;
    private final OFDRepository ofdRepository;

    private static void validateID(CashRegister cashRegister) {
        if (cashRegister.getId() == null) {
            throw new RuntimeException("ID Кассы не заполнен. Обновление невозможно.");
        }
    }

    @Override
    public List<CashRegister> getAll() {
        return cashRegisterRepository.findAll();
    }

    @Override
    @Transactional
    public Long create(CashRegister cashRegister) {
        cashRegister.setArchive(false);
        return cashRegisterRepository.save(cashRegister).getId();
    }

    @Override
    @Transactional
    public void update(CashRegister cashRegister) {
        validateID(cashRegister);
        cashRegisterRepository.save(cashRegister);
    }

    @Override
    @Transactional
    public void archive(Long id) {
        var cashRegister = cashRegisterRepository.findById(id).orElseThrow();
        cashRegister.setArchive(true);
        cashRegisterRepository.save(cashRegister);
    }

    @Override
    @Transactional
    public void recover(Long id) {
        var cashRegister = cashRegisterRepository.findById(id).orElseThrow();
        cashRegister.setArchive(false);
        cashRegisterRepository.save(cashRegister);
    }

    @Override
    public CashRegisterDTO toDTO(CashRegister cashRegister) {
        if (cashRegister == null) {
            return null;
        }
        return CashRegisterDTO.builder()
                .id(cashRegister.getId())
                .factoryNumber(cashRegister.getFactoryNumber())
                .installationAddress(cashRegister.getInstallationAddress())
                .registrationNumber(cashRegister.getRegistrationNumber())
                .archive(cashRegister.getArchive())
                .clientId(cashRegister.getClient() != null ? cashRegister.getClient().getId() : null)
                .modelId(cashRegister.getModel() != null ? cashRegister.getModel().getId() : null)
                .taxationSystemId(cashRegister.getTaxationSystem() != null ? cashRegister.getTaxationSystem().getId() : null)
                .ofdId(cashRegister.getOfd() != null ? cashRegister.getOfd().getId() : null)
                .clientTitle(cashRegister.getClient() != null ? cashRegister.getClient().getTitle() : null)
                .modelTitle(cashRegister.getModel() != null ? cashRegister.getModel().getTitle() : null)
                .ofdTitle(cashRegister.getOfd() != null ? cashRegister.getOfd().getTitle() : null)
                .taxationSystemTitle(cashRegister.getTaxationSystem() != null ? cashRegister.getTaxationSystem().getTitle() : null)
                .activationDate(cashRegister.getActivationDate())
                .deactivationDate(cashRegister.getDeactivationDate())
                .fiscalType(cashRegister.getFiscalType())
                .fiscalFactoryNumber(cashRegister.getFiscalFactoryNumber())
                .fiscalActivationDate(cashRegister.getFiscalActivationDate())
                .fiscalDeactivationDate(cashRegister.getFiscalDeactivationDate())
                .version(cashRegister.getVersion())
                .installationDate(cashRegister.getInstallationDate())
                .build();
    }

    @Override
    public CashRegister toEntity(CashRegisterDTO dto) {
        if (dto == null) {
            return null;
        }

        CashRegister cashRegister = new CashRegister();
        cashRegister.setId(dto.getId());
        cashRegister.setFactoryNumber(dto.getFactoryNumber());
        cashRegister.setInstallationAddress(dto.getInstallationAddress());
        cashRegister.setRegistrationNumber(dto.getRegistrationNumber());

        Optional.ofNullable(dto.getClientId()).ifPresent(
                client -> cashRegister.setClient(clientRepository.getReferenceById(client))
        );
        Optional.ofNullable(dto.getOfdId()).ifPresent(
                ofd -> cashRegister.setOfd(ofdRepository.getReferenceById(ofd))
        );
        Optional.ofNullable(dto.getModelId()).ifPresent(
                model -> cashRegister.setModel(modelRepository.getReferenceById(model))
        );
        Optional.ofNullable(dto.getTaxationSystemId()).ifPresent(
                taxationSystem -> cashRegister.setTaxationSystem(taxationSystemRepository.getReferenceById(taxationSystem))
        );

        cashRegister.setActivationDate(dto.getActivationDate());
        cashRegister.setDeactivationDate(dto.getDeactivationDate());

        cashRegister.setFiscalType(dto.getFiscalType());
        cashRegister.setFiscalFactoryNumber(dto.getFiscalFactoryNumber());
        cashRegister.setFiscalActivationDate(dto.getFiscalActivationDate());
        cashRegister.setFiscalDeactivationDate(dto.getFiscalDeactivationDate());

        cashRegister.setVersion(dto.getVersion());
        cashRegister.setInstallationDate(dto.getInstallationDate());

        cashRegister.setArchive(dto.getArchive());

        return cashRegister;
    }
}
