package com.example.second;

import com.example.common.PassingCar;

import java.util.function.Consumer;

public class SecondDemo {
    public static void main(String[] args) {
        TrafficCamera speedCamera = new TrafficCamera();
        Consumer<PassingCar> carNotificationConsumer = (passingCar) -> {
            System.out.println(passingCar);
        };

        Consumer<PassingCar> taxiNotificationConsumer = (passingCar) -> {
            if (passingCar.isTaxi) {
                System.out.println("It's taxi!");
            }
        };

        // Pull approach
        speedCamera.setConsumer(
            carNotificationConsumer.andThen(taxiNotificationConsumer)
        );

        new Thread(speedCamera).start();
    }
}
