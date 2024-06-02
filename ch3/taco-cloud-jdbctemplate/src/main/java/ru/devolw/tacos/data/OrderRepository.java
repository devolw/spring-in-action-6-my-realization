package ru.devolw.tacos.data;

import ru.devolw.tacos.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
