package com.example.first;

import com.example.common.PassingCar;

public class TrafficObserver implements Runnable {
    private TrafficCamera trafficCamera;

    public TrafficObserver(TrafficCamera trafficCamera) {
        this.trafficCamera = trafficCamera;
    }

    @Override
    public void run() {
        // Pull approach
        while (true) {
            if (trafficCamera.hasNextCar()) {
                processCar(trafficCamera.pollNextCar());
            }
        }
    }

    private void processCar(PassingCar car) {
        System.out.println(car);
    }
}
