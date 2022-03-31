package com.lpnu.swqm.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class Car implements SpareParts {

    private final String producer;
    private final String model;
    private final Type type;
    private final Engine engine;
    private final Wheels wheels;

    @Builder
    public Car(String producer, String model, Type type, Engine engine, Wheels wheels) {
        this.producer = producer;
        this.model = model;
        this.type = type;
        this.engine = engine;
        this.wheels = wheels;
    }

    public enum Type {
        SUV, TRACK, SEDAN, VAN, COUPE, WAGON, CONVERTIBLE, SPORT, CROSSOVER, LUXURY
    }
}
