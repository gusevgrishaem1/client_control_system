package com.techls.ofd.services.client;

import com.techls.ofd.entities.Client;
import com.techls.ofd.repositories.cashregister.CashRegisterRepository;
import com.techls.ofd.repositories.client.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CashRegisterRepository cashRegisterRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getCombo() {
        return clientRepository
                .findAll().stream()
                .filter(el -> el.getArchive() == null || !el.getArchive())
                .filter(el -> !Strings.isEmpty(el.getTitle()))
                .toList();
    }

    @Override
    @Transactional
    public Long create(Client client) {
        client.setArchive(false);
        return clientRepository.save(client).getId();
    }

    @Override
    @Transactional
    public void update(Client client) {
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void archive(Long id) {
        cashRegisterRepository.findByClient_Id(id).forEach(
                e -> {
                    e.setArchive(true);
                    cashRegisterRepository.save(e);
                }
        );
        clientRepository.findById(id).ifPresent(
                client -> {
                    client.setArchive(true);
                    clientRepository.save(client);
                }
        );
    }

    @Override
    public void recover(Long id) {
        cashRegisterRepository.findByClient_Id(id).forEach(
                e -> {
                    e.setArchive(false);
                    cashRegisterRepository.save(e);
                }
        );
        clientRepository.findById(id).ifPresent(
                client -> {
                    client.setArchive(false);
                    clientRepository.save(client);
                }
        );
    }
}
