package ru.devolw.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.devolw.tacos.model.TacoOrder;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}