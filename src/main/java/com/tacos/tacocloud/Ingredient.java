package com.tacos.tacocloud;

import lombok.Data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class Ingredient {

    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    // Convenient constructor for creating new objects
    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    // enum is used to specifically write down the only things you require the user to input or that you accept, 
    // predefined values of what you want , it a special data type
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
 }
