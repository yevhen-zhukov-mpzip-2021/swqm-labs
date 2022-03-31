package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Engine;
import com.lpnu.swqm.exceptions.EngineNotGivenException;
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

public class EngineServiceTest {

    private static final Logger log = LogManager.getLogger(EngineServiceTest.class.getName());

    private static final String SN_1 = "SN_1";
    private static final String SN_4 = "SN_4";

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
        List<Engine> engineList = Arrays.asList(
                Engine.builder().serialNumber(SN_1).build(),
                Engine.builder().serialNumber("SN_2").build(),
                Engine.builder().serialNumber("SN_3").build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = engineList;

        return data;
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindBySerialNumberSuccess(List<Engine> engines) {
        EngineService engineService = new EngineService();
        Engine filteredBySerialNumber = engineService.findBySerialNumber(
                engines, SN_1);

        Assert.assertNotNull(filteredBySerialNumber);
        Assert.assertEquals(filteredBySerialNumber.getSerialNumber(), SN_1);
    }

    @Test(dataProvider = "engineDataProvider")
    public void testFindBySerialNumberFailSerialNumberNotFound(List<Engine> engines) {
        EngineService engineService = new EngineService();
        Engine filteredBySerialNumber = engineService.findBySerialNumber(
                engines, SN_4);

        Assert.assertNull(filteredBySerialNumber);
    }

    @Test(expectedExceptions = EngineNotGivenException.class)
    public void testFindBySerialNumberFailDepartmentIsNull() {
        EngineService engineService = new EngineService();
        engineService.findBySerialNumber(Collections.emptyList(), null);
    }

    @Test
    public void testFindBySerialNumberFailEmployeesNotGiven() {
        EngineService engineService = new EngineService();
        Engine filteredBySerialNumber = engineService.findBySerialNumber(
                Collections.emptyList(), SN_1);

        Assert.assertNull(filteredBySerialNumber);
    }
}