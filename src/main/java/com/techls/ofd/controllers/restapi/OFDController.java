package com.techls.ofd.controllers.restapi;

import com.techls.ofd.entities.OFD;
import com.techls.ofd.services.ofd.OFDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/ofds")
public class OFDController {
    private final OFDService ofdService;

    @GetMapping
    public List<OFD> getAll() {
        return ofdService.getAll();
    }

    @PostMapping
    public Long create(@RequestBody OFD ofd) {
        return ofdService.create(ofd);
    }

    @PutMapping
    public void update(@RequestBody OFD ofd) {
        ofdService.update(ofd);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        ofdService.delete(id);
    }

    @GetMapping("/combo")
    public List<OFD> getCombo() {
        return ofdService.getCombo();
    }
}
