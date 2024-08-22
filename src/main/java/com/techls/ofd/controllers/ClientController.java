package com.techls.ofd.controllers;

import com.techls.ofd.dto.client.ClientFilterDTO;
import com.techls.ofd.entities.Client;
import com.techls.ofd.services.clients.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    // Метод для получения клиентов с фильтрацией и сортировкой
    @GetMapping
    public ResponseEntity<Page<Client>> getClients(ClientFilterDTO filterDTO) {
        Page<Client> clients = clientService.getClients(filterDTO);
        return ResponseEntity.ok(clients);
    }

    // Метод для добавления нового клиента
    @PostMapping
    public void addClient(@RequestBody Client client) {
        clientService.addClient(client);
    }

    // Метод для обновления списка клиентов
    @PutMapping
    public void updateClients(@RequestBody List<Client> clients) {
        clientService.updateClients(clients);
    }

    // Метод для для удаления списка клиентов
    @DeleteMapping
    public void deleteClients(@RequestParam Long id) {
        clientService.deleteClients(List.of(id));
    }
}
