package ru.devolw.tacos.udt;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.*;
import ru.devolw.tacos.model.Ingredient;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {

    private final String name;

    private final Ingredient.Type type;

}