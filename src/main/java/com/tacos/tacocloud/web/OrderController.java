package com.tacos.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import com.tacos.tacocloud.TacoOrder;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;

import org.springframework.ui.Model;

import com.tacos.tacocloud.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    //  the method below will be allowed to process the submitted data if there are no
    //validation errors. If there are validation errors, the request will be forwarded to the
    //form view to give the user a chance to correct their mistakes.

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
           return "orderForm";
        }
        orderRepo.save(order);
        sessionStatus.setComplete();
        
       return "redirect:/";
    }
}
