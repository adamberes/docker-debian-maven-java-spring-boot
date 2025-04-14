package com.example.demo.service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomService {
    public int generateRandomNumber() {
        return new Random().ints(10, 51).findFirst().getAsInt();
    }
}
