package com.techls.ofd.repositories.cashregister;

import com.techls.ofd.entities.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
    List<CashRegister> findByClient_Id(Long clientID);

    List<CashRegister> findByModel_Id(Long modelID);

    List<CashRegister> findByOfd_Id(Long ofdID);

    List<CashRegister> findByTaxationSystem_Id(Long taxationSystemID);
}
