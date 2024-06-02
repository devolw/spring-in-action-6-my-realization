package ru.devolw.tacos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.devolw.tacos.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.devolw.tacos.repository.OrderRepository;
import ru.devolw.tacos.model.TacoOrder;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal Client client,
                            @ModelAttribute TacoOrder order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(client.getFullName());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(client.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(client.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(client.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(client.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal Client client) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

        order.setClient(client);

        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}