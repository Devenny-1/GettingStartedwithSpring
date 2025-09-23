package com.tacos.tacocloud.data;

import java.util.Optional;
import com.tacos.tacocloud.Ingredient;

public interface IngredientRepository {
    
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);

}

