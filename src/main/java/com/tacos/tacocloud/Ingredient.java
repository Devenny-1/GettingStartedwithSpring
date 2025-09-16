package com.tacos.tacocloud;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;
    
    // enum is used to specifically write down the only things you require the user to input or that you accept, 
    // predefined values of what you want , it a special data type
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
 }
