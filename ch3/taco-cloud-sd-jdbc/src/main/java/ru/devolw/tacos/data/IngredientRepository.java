package ru.devolw.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.devolw.tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}