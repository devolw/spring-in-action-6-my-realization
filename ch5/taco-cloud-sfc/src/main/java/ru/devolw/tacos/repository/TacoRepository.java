package ru.devolw.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.devolw.tacos.model.Taco;


public interface TacoRepository extends CrudRepository<Taco, Long> {
}