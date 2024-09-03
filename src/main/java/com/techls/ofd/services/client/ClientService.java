package com.techls.ofd.services.client;

import com.techls.ofd.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAll();

    List<Client> getCombo();

    Long create(Client client);

    void update(Client client);

    void archive(Long id);

    void recover(Long id);
}
