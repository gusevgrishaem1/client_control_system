package com.techls.ofd.services.taxationsystem;

import com.techls.ofd.entities.TaxationSystem;
import com.techls.ofd.repositories.cashregister.CashRegisterRepository;
import com.techls.ofd.repositories.taxationsystem.TaxationSystemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaxationSystemServiceImpl implements TaxationSystemService {
    private final TaxationSystemRepository taxationSystemRepository;
    private final CashRegisterRepository cashRegisterRepository;

    @Override
    @Transactional
    public Long create(TaxationSystem taxationSystem) {
        return taxationSystemRepository.save(taxationSystem).getId();
    }

    @Override
    @Transactional
    public void update(TaxationSystem taxationSystem) {
        taxationSystemRepository.save(taxationSystem);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cashRegisterRepository.findByTaxationSystem_Id(id).forEach(
                cashRegister -> {
                    cashRegister.setArchive(true);
                    cashRegisterRepository.save(cashRegister);
                }
        );
        taxationSystemRepository.findById(id).ifPresent(
                taxationSystem -> {
                    taxationSystem.setArchive(true);
                    taxationSystemRepository.save(taxationSystem);
                }
        );
    }

    @Override
    public void recover(Long id) {
        cashRegisterRepository.findByTaxationSystem_Id(id).forEach(
                cashRegister -> {
                    cashRegister.setArchive(false);
                    cashRegisterRepository.save(cashRegister);
                }
        );
        taxationSystemRepository.findById(id).ifPresent(
                taxationSystem -> {
                    taxationSystem.setArchive(false);
                    taxationSystemRepository.save(taxationSystem);
                }
        );
    }

    @Override
    public List<TaxationSystem> getAll() {
        return taxationSystemRepository.findAll();
    }

    @Override
    public List<TaxationSystem> getCombo() {
        return taxationSystemRepository.findAll()
                .stream()
                .filter(el -> !el.getArchive())
                .filter(el -> el.getTitle() != null).toList();
    }

}
