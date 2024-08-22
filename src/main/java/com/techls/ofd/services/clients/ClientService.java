package com.techls.ofd.services.clients;

import com.techls.ofd.dto.client.ClientFilterDTO;
import com.techls.ofd.entities.Client;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    Page<Client> getClients(ClientFilterDTO filterDTO);

    void addClient(Client client);

    void updateClients(List<Client> clients);

    void deleteClients(List<Long> ids);
}
