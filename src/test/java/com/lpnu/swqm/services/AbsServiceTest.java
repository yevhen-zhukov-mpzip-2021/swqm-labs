package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Abs;
import com.lpnu.swqm.exceptions.AbsTypeNotGivenException;
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

public class AbsServiceTest {

    private static final Logger log = LogManager.getLogger(AbsServiceTest.class.getName());

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "absDataProvider")
    public Object[][] absDataProvider() {
        List<Abs> wheelsList = Arrays.asList(
                Abs.builder().type(Abs.Type.FOUR_CHANNEL_SIGNAL).build(),
                Abs.builder().type(Abs.Type.THREE_CHANNEL_SIGNAL).build(),
                Abs.builder().type(Abs.Type.ONE_CHANNEL_SIGNAL).build(),
                Abs.builder().type(Abs.Type.FOUR_CHANNEL_SIGNAL).build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = wheelsList;

        return data;
    }

    @Test(dataProvider = "absDataProvider")
    public void testFindByAbsTypeSuccess(List<Abs> absList) {
        AbsService absService = new AbsService();
        List<Abs> filteredByType = absService.findByType(
                absList, Abs.Type.FOUR_CHANNEL_SIGNAL);

        Assert.assertFalse(filteredByType.isEmpty());
        Assert.assertEquals(filteredByType.size(), 2);
    }

    @Test(expectedExceptions = AbsTypeNotGivenException.class)
    public void testFindByAbsTypeFailDepartmentIsNull() {
        AbsService absService = new AbsService();
        absService.findByType(
                Collections.emptyList(), null);
    }

    @Test
    public void testFindByAbsTypeFailEmployeesNotGiven() {
        AbsService absService = new AbsService();
        List<Abs> filteredByType = absService.findByType(
                Collections.emptyList(), Abs.Type.FOUR_CHANNEL_SIGNAL);

        Assert.assertTrue(filteredByType.isEmpty());
    }
}