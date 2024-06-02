package ru.devolw.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.devolw.tacos.model.TacoOrder;


public interface OrderRepository extends CrudRepository<TacoOrder, String> {

}