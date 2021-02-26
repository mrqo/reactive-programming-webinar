package com.example.first;

import com.example.common.PassingCar;
import com.example.common.PassingCarGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TrafficCamera implements Runnable {
    private BlockingQueue<PassingCar> cars = new ArrayBlockingQueue<PassingCar>(1000);

    public boolean hasNextCar() {
        return !cars.isEmpty();
    }

    public PassingCar pollNextCar() {
        return cars.poll();
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
        cars.add(PassingCarGenerator.generate());
    }
}
