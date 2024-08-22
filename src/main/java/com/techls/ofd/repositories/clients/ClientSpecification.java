package com.techls.ofd.repositories.clients;

import com.techls.ofd.entities.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {
    public static Specification<Client> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id != null ? criteriaBuilder.equal(root.get("id"), id) : null;
    }

    public static Specification<Client> hasInn(String inn) {
        return (root, query, criteriaBuilder) ->
                inn != null ? criteriaBuilder.like(root.get("inn"), "%" + inn + "%") : null;
    }

    public static Specification<Client> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title != null ? criteriaBuilder.like(root.get("title"), "%" + title + "%") : null;
    }

    public static Specification<Client> hasTelephone(String telephone) {
        return (root, query, criteriaBuilder) ->
                telephone != null ? criteriaBuilder.like(root.get("telephone"), "%" + telephone + "%") : null;
    }
}
