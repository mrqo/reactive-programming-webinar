package com.example.second;

import com.example.common.PassingCar;
import com.example.common.PassingCarGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class TrafficCamera implements Runnable {
    private BlockingQueue<PassingCar> cars = new ArrayBlockingQueue<PassingCar>(1000);
    private Consumer<PassingCar> consumer = null;

    public void setConsumer(Consumer<PassingCar> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                registerNewCar();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerNewCar() {
        PassingCar newCar = PassingCarGenerator.generate();
        cars.add(newCar);

        if (consumer != null) {
            consumer.accept(newCar);
        }
    }
}
