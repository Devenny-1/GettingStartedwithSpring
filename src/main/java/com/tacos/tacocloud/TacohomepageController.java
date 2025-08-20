package com.tacos.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller           
public class TacohomepageController {
    @GetMapping("/")     
    public String home() {
    return "home"; 
  }
}

