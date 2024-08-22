package com.techls.ofd.services.clients;

import com.techls.ofd.dto.client.ClientFilterDTO;
import com.techls.ofd.entities.Client;
import com.techls.ofd.repositories.clients.ClientRepository;
import com.techls.ofd.repositories.clients.ClientSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Page<Client> getClients(ClientFilterDTO filterDTO) {
        Specification<Client> spec = Specification.where(ClientSpecification.hasId(filterDTO.getId()))
                .and(ClientSpecification.hasInn(filterDTO.getInn()))
                .and(ClientSpecification.hasTitle(filterDTO.getTitle()))
                .and(ClientSpecification.hasTelephone(filterDTO.getTelephone()));

        Sort sort = Sort.by(Sort.Direction.fromString(filterDTO.getSortDirection()), filterDTO.getSortColumn());
        Pageable pageable = PageRequest.of(filterDTO.getPage(), filterDTO.getSize(), sort);

        return clientRepository.findAll(spec, pageable);
    }

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClients(List<Client> clients) {
        clientRepository.saveAll(clients);
    }

    @Override
    @Transactional
    public void deleteClients(List<Long> ids) {
        ids.forEach(clientRepository::deleteById);
    }
}
