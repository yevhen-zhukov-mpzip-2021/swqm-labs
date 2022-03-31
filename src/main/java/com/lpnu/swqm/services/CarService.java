package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Car;
import com.lpnu.swqm.exceptions.CarTypeNotGivenException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

@Getter
public class CarService {

    private static final Logger log = LogManager.getLogger(CarService.class.getName());

    public Car findByType(List<Car> carList,
                          Car.Type caType) {
        if (Objects.isNull(caType)) {
            throw new CarTypeNotGivenException("Car type is null!");
        } else if (carList.isEmpty()) {
            log.warn("Car-list is empty");
            return null;
        }

        return carList.stream()
                .filter(car -> car.getType().equals(caType))
                .findFirst()
                .orElseGet(() -> {
                    log.warn("No any Engine found by car type {}", caType);
                    return null;
                });
    }
}
