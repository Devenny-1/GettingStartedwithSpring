package com.tacos.tacocloud.web;

import java.util.stream.StreamSupport;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
//import java.util.Arrays;
//import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import com.tacos.tacocloud.Ingredient;
import com.tacos.tacocloud.Ingredient.Type;
import com.tacos.tacocloud.Taco;
import com.tacos.tacocloud.TacoOrder;
import com.tacos.tacocloud.data.IngredientRepository; 

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
        model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
    }
    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
    }

    // this block of code performs validation of the user's input submitted on TacoOrder objects
    // similar changes are also required in the processOrder() method of OrderController
    
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

      if (errors.hasErrors()){
        return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}