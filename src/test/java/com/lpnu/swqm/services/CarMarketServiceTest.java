package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Abs;
import com.lpnu.swqm.domain.Car;
import com.lpnu.swqm.domain.CarMarket;
import com.lpnu.swqm.domain.Engine;
import com.lpnu.swqm.domain.SpareParts;
import com.lpnu.swqm.domain.Wheels;
import com.lpnu.swqm.exceptions.SparePartsTypeNotGivenException;
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

public class CarMarketServiceTest {

    private static final Logger log = LogManager.getLogger(CarMarketServiceTest.class.getName());

    private static final Class<Car> TYPE_CAR = Car.class;

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
        List<SpareParts> spareParts = Arrays.asList(
                Car.builder().build(),
                Wheels.builder().build(),
                Abs.builder().build()
        );
        CarMarket carMarket = CarMarket.builder().spareParts(spareParts).build();

        Object[][] data = new Object[1][1];
        data[0][0] = carMarket;

        return data;
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindBySparePartsTypeSuccess(CarMarket carMarket) {
        CarMarketService carMarketService = new CarMarketService();
        List<Car> filteredByType = carMarketService.findByType(
                carMarket.getSpareParts(), TYPE_CAR);

        Assert.assertNotNull(filteredByType);
        Assert.assertFalse(filteredByType.isEmpty());
        Assert.assertTrue(filteredByType.stream().allMatch(car -> car.getClass().equals(TYPE_CAR)));
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindBySparePartsTypeFailSerialNumberNotFound(CarMarket carMarket) {
        CarMarketService carMarketService = new CarMarketService();
        List<Engine> filteredByType = carMarketService.findByType(
                carMarket.getSpareParts(), Engine.class);

        Assert.assertNotNull(filteredByType);
        Assert.assertTrue(filteredByType.isEmpty());
    }

    @Test(expectedExceptions = SparePartsTypeNotGivenException.class)
    public void testFindBySparePartsTypeFailDepartmentIsNull() {
        CarMarketService carMarketService = new CarMarketService();
        carMarketService.findByType(
                Collections.emptyList(), null);
    }

    @Test
    public void testFindBySparePartsTypeFailEmployeesNotGiven() {
        CarMarketService carMarketService = new CarMarketService();
        List<Car> filteredByType = carMarketService.findByType(
                Collections.emptyList(), TYPE_CAR);

        Assert.assertTrue(filteredByType.isEmpty());
    }
}