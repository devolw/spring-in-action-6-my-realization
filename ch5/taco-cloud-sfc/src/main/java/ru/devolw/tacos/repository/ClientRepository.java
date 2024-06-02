package ru.devolw.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.devolw.tacos.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByUsername(String username);
}
