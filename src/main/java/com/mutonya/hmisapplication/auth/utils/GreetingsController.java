package com.mutonya.hmisapplication.auth.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/api/home")

    public String greetings(){
        return "Hello George";
    }
}



