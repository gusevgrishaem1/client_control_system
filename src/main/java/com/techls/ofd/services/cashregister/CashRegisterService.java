package com.techls.ofd.services.cashregister;

import com.techls.ofd.dto.CashRegisterDTO;
import com.techls.ofd.entities.CashRegister;

import java.util.List;

public interface CashRegisterService {
    List<CashRegister> getAll();

    Long create(CashRegister cashRegister);

    void update(CashRegister cashRegister);

    void archive(Long id);

    void recover(Long id);

    CashRegisterDTO toDTO(CashRegister cashRegister);

    CashRegister toEntity(CashRegisterDTO dto);
}
