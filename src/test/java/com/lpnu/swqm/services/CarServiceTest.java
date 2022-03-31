package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Car;
import com.lpnu.swqm.exceptions.CarTypeNotGivenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarServiceTest {

    private static final Logger log = LogManager.getLogger(CarServiceTest.class.getName());

    private static final Car.Type TYPE_1 = Car.Type.SEDAN;
    private static final Car.Type TYPE_2 = Car.Type.SPORT;

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "engineDataProvider")
    public Object[][] engineDataProvider() {
        List<Car> carList = Arrays.asList(
                Car.builder().type(TYPE_1).build(),
                Car.builder().type(TYPE_2).build(),
                Car.builder().type(TYPE_1).build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = carList;

        return data;
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindByCarTypeSuccess(List<Car> cars) {
        CarService carService = new CarService();
        Car filteredByCarType = carService.findByType(
                cars, TYPE_1);

        Assert.assertNotNull(filteredByCarType);
        Assert.assertEquals(filteredByCarType.getType(), TYPE_1);
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindByCarTypeFailSerialNumberNotFound(List<Car> cars) {
        CarService carService = new CarService();
        Car filteredByCarType = carService.findByType(
                cars, Car.Type.CROSSOVER);

        Assert.assertNull(filteredByCarType);
    }

    @Test(expectedExceptions = CarTypeNotGivenException.class)
    public void testFindByCarTypeFailDepartmentIsNull() {
        CarService carService = new CarService();
        carService.findByType(Collections.emptyList(), null);
    }

    @Test
    public void testFindByCarTypeFailEmployeesNotGiven() {
        CarService carService = new CarService();
        Car filteredByCarType = carService.findByType(
                Collections.emptyList(), TYPE_1);

        Assert.assertNull(filteredByCarType);
    }
}