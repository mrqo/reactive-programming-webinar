package com.example.common;

import java.util.List;
import java.util.Random;

public class PassingCarGenerator {
    private final static List<String> MANUFACTURERS = List.of(
        "audi",
        "rolls-royce",
        "ford",
        "mercedes",
        "skoda",
        "toyota"
    );

    public static PassingCar generate() {
        Random rnd = new Random();

        double speed = Math.ceil(Math.abs(rnd.nextDouble()) * 100 % 70 + 30);
        String manufacturer = MANUFACTURERS.get(Math.abs(rnd.nextInt()) % MANUFACTURERS.size());
        boolean isTaxi = rnd.nextBoolean();

        return new PassingCar(speed, manufacturer, isTaxi);
    }
}
