package com.tacos.tacocloud;

import java.util.List;
import java.util.Date;


//import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// import org.antlr.v4.runtime.misc.NotNull;

import lombok.Data;

@Data
public class Taco {
    
    private long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
