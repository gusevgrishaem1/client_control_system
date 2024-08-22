package com.techls.ofd.repositories.clients;

import com.techls.ofd.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

}
