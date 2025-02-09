package ru.devolw.tacos.data;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.devolw.tacos.model.Ingredient;
import ru.devolw.tacos.model.IngredientRef;
import ru.devolw.tacos.model.Taco;
import ru.devolw.tacos.model.TacoOrder;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    // Сохранение заказа
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco_Order "
                + "(delivery_name, delivery_street, delivery_city, "
                + "delivery_state, delivery_zip, card_number, "
                + "card_expiration, card_cvv, placed_at) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCardNumber(),
                        order.getCardExpiration(),
                        order.getCardCVV(),
                        order.getPlacedAt())
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for(Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }

        return order;
    }

    // Сохранение тако
    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco "
                + "(name, created_at, taco_order, taco_order_key) "
                + "values(?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCreatedAt(),
                        orderId,
                        orderKey)
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

//        BUG
//        saveIngredientRefs(tacoId, taco.getIngredients());

        List<Ingredient> ingredients = taco.getIngredients();
        int i = 0;
        for(Ingredient ingredient : ingredients) {
            saveIngredientRefs(tacoId, i++, ingredient);
        }

        return tacoId;
    }

//    BUG
//    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
//        int key = 0;
//        for (IngredientRef ingredientRef : ingredientRefs) {
//            jdbcOperations.update(
//                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
//                            + "values (?, ?, ?)",
//                    ingredientRef.getIngredient(), tacoId, key++
//            );
//        }
//    }

    private void saveIngredientRefs(long tacoID, int tacoKey, Ingredient ingredient) {
        jdbcOperations.update(
                "insert into Ingredient_Ref (" +
                        "ingredient, " +
                        "taco, " +
                        "taco_key) " +
                        "values (?, ?, ?)",
                ingredient.getId(),
                tacoID,
                tacoKey
        );
    }
}
