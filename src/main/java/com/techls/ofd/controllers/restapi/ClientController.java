package com.techls.ofd.controllers.restapi;

import com.techls.ofd.entities.Client;
import com.techls.ofd.services.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/combo")
    public List<Client> getCombo() {
        return clientService.getCombo();
    }

    @PostMapping
    public Long create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping
    public void update(@RequestBody Client client) {
        clientService.update(client);
    }

    @DeleteMapping
    public void archive(@RequestParam Long id) {
        clientService.archive(id);
    }

    @PutMapping("/recover")
    public void recover(@RequestParam Long id) {
        clientService.recover(id);
    }
}
