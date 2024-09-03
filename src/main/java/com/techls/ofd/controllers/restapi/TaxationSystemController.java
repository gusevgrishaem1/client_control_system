package com.techls.ofd.controllers.restapi;

import com.techls.ofd.entities.TaxationSystem;
import com.techls.ofd.services.taxationsystem.TaxationSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/taxation-systems")
public class TaxationSystemController {
    private final TaxationSystemService taxationSystemService;

    @GetMapping
    public List<TaxationSystem> getAll() {
        return taxationSystemService.getAll();
    }

    @PostMapping
    public Long create(@RequestBody TaxationSystem taxationSystem) {
        return taxationSystemService.create(taxationSystem);
    }

    @PutMapping
    public void update(@RequestBody TaxationSystem taxationSystem) {
        taxationSystemService.update(taxationSystem);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        taxationSystemService.delete(id);
    }

    @GetMapping("/combo")
    public List<TaxationSystem> getCombo() {
        return taxationSystemService.getCombo();
    }
}
