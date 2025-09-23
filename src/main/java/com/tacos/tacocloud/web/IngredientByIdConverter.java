package com.tacos.tacocloud.web;

//import java.util.HashMap;
//import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tacos.tacocloud.Ingredient;
//import com.tacos.tacocloud.Ingredient.Type;
import com.tacos.tacocloud.data.IngredientRepository;
import org.springframework.lang.NonNull;
// import org.springframework.lang.Nullable;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    
    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}

