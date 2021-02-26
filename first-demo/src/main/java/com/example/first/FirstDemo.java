package com.example.first;

public class FirstDemo {
    public static void main(String[] args) {
        TrafficCamera trafficCamera = new TrafficCamera();
        TrafficObserver trafficObserver = new TrafficObserver(trafficCamera);

        new Thread(trafficObserver).start();
        new Thread(trafficCamera).start();
    }
}
