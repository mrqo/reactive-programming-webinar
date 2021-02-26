package com.example.thirddemo;

import com.example.common.PassingCar;
import com.example.common.PassingCarGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Slf4j
@Component
public class MonitoringService {
    private final static List<PassingCar> cars = List.of(
        new PassingCar(45.0, "mercedes", true),
        new PassingCar(60.0, "audi", false),
        new PassingCar(70.0, "toyota", true),
        new PassingCar(51.0, "mercedes", false),
        new PassingCar(300.0, "bugatti", false)
    );

    private final static List<PassingCar> otherCars = List.of(
        new PassingCar(90.0, "SCANIA", true),
        new PassingCar(41.0, "MERCEDES", false),
        new PassingCar(55.0, "LOTUS", true),
        new PassingCar(89.0, "DODGE", false),
        new PassingCar(30.0, "AUDI", false)
    );

    private final int SPEED_LIMIT = 50;

//    @EventListener
    public void mono(ContextRefreshedEvent event) {
        Mono.just(PassingCarGenerator.generate())
            .subscribe(System.out::println);
    }

//    @EventListener
    public void printAllCars(ContextRefreshedEvent event) {
        Flux.fromIterable(cars)
            .delayElements(Duration.ofSeconds(1))
            .subscribe(System.out::println);
    }

//    @EventListener
    public void printOnlyTaxis(ContextRefreshedEvent event) {
        Flux.fromIterable(cars)
            .delayElements(Duration.ofSeconds(1))
            .filter(car -> car.isTaxi)
            .subscribe(car -> System.out.println("That's taxi! Stop, " + car.manufacturer + "!"));
    }

//    @EventListener
    public void mapToManufacturer(ContextRefreshedEvent event) {
        Flux.fromIterable(cars)
            .delayElements(Duration.ofSeconds(1))
            .map(car -> car.manufacturer)
            .map(manufacturer -> manufacturer + " just passed by")
            .subscribe(System.out::println);
    }

//    @EventListener
    public void mergeMultipleStreams(ContextRefreshedEvent event) {
        var firstCamera =  Flux.fromIterable(cars)
            .delayElements(Duration.ofMillis(900));

        var secondCamera = Flux.fromIterable(otherCars)
            .delayElements(Duration.ofMillis(500));

        firstCamera.mergeWith(secondCamera)
            .subscribe(System.out::println);
    }

//    @EventListener
    public void hotStream(ContextRefreshedEvent event) throws InterruptedException {
        var cameraStream = Flux.interval(Duration.ofSeconds(1))
            .map(time -> PassingCarGenerator.generate())
            .share();

        cameraStream.subscribe(car -> {
            System.out.println("I see " + car);
        });

        cameraStream.filter(car -> car.kmh > SPEED_LIMIT)
            .delaySubscription(Duration.ofSeconds(3))
            .filter(car -> car.isTaxi)
            .subscribe(car -> System.out.println(car + " taxi just exceeded the limit!!"));
    }
}
