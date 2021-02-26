package com.example.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PassingCar {
    public double kmh;
    public String manufacturer;
    public boolean isTaxi;
}
