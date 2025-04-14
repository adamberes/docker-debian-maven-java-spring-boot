package com.example.demo.controller;

import com.example.demo.service.RandomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class RandomController {

    @GetMapping("/random")
    public String random() {
        int randomNumber = new RandomService().generateRandomNumber();
        System.out.println("Random number: " + randomNumber);
        return "Random number: " + randomNumber;
    }
}
