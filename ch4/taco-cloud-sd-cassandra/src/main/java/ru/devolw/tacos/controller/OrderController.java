package ru.devolw.tacos.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.devolw.tacos.model.Taco;
import ru.devolw.tacos.repository.OrderRepository;
import ru.devolw.tacos.model.TacoOrder;
import ru.devolw.tacos.repository.TacoRepository;
import ru.devolw.tacos.udt.TacoUDT;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;
    private TacoRepository tacoRepo;

    public OrderController(OrderRepository orderRepo,
                           TacoRepository tacoRepo) {
        this.orderRepo = orderRepo;
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

        orderRepo.save(order);
        for (TacoUDT tacoUDT : order.getTacos()) {
            Taco taco = new Taco();
            taco.setName(tacoUDT.getName());
            taco.setIngredients(tacoUDT.getIngredients());
            tacoRepo.save(taco);
        }

        sessionStatus.setComplete();

        return "redirect:/";
    }
}