package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Wheels;
import com.lpnu.swqm.exceptions.WheelsTypeNotGivenException;
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

public class WheelsServiceTest {

    private static final Logger log = LogManager.getLogger(WheelsServiceTest.class.getName());

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "wheelsDataProvider")
    public Object[][] wheelsDataProvider() {
        List<Wheels> wheelsList = Arrays.asList(
                Wheels.builder().type(Wheels.Type.CHROME).build(),
                Wheels.builder().type(Wheels.Type.DIAMOND).build(),
                Wheels.builder().type(Wheels.Type.FORGED).build(),
                Wheels.builder().type(Wheels.Type.DIAMOND).build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = wheelsList;

        return data;
    }

    @Test(dataProvider = "wheelsDataProvider")
    public void testFindByWheelsTypeSuccess(List<Wheels> wheelsList) {
        WheelsService wheelsService = new WheelsService();
        List<Wheels> filteredByType = wheelsService.findByType(
                wheelsList, Wheels.Type.DIAMOND);

        Assert.assertFalse(filteredByType.isEmpty());
        Assert.assertEquals(filteredByType.size(), 2);
    }

    @Test(expectedExceptions = WheelsTypeNotGivenException.class)
    public void testFindByWheelsTypeFailDepartmentIsNull() {
        WheelsService wheelsService = new WheelsService();
        wheelsService.findByType(Collections.emptyList(), null);
    }

    @Test
    public void testFindByWheelsTypeFailEmployeesNotGiven() {
        WheelsService wheelsService = new WheelsService();
        List<Wheels> filteredByType = wheelsService.findByType(
                Collections.emptyList(), Wheels.Type.DIAMOND);

        Assert.assertTrue(filteredByType.isEmpty());
    }
}