package com.techls.ofd.controllers.restapi;

import com.techls.ofd.dto.CashRegisterDTO;
import com.techls.ofd.services.cashregister.CashRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cash-registers")
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;

    @GetMapping
    public List<CashRegisterDTO> getAll() {
        return cashRegisterService.getAll().stream().map(cashRegisterService::toDTO).toList();
    }

    @PostMapping
    public Long create(@RequestBody CashRegisterDTO dto) {
        return cashRegisterService.create(cashRegisterService.toEntity(dto));
    }

    @PutMapping
    public void update(@RequestBody CashRegisterDTO dto) {
        cashRegisterService.update(cashRegisterService.toEntity(dto));
    }

    @DeleteMapping
    public void archive(@RequestParam Long id) {
        cashRegisterService.archive(id);
    }

    @PutMapping("/recover")
    public void recover(@RequestParam Long id) {
        cashRegisterService.recover(id);
    }
}
