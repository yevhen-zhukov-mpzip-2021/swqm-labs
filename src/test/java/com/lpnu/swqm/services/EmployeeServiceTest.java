package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Employee;
import com.lpnu.swqm.exceptions.NotGivenTaxIdException;
import org.apache.commons.lang3.StringUtils;
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

public class EmployeeServiceTest {

    private static final Logger log = LogManager.getLogger(EmployeeServiceTest.class.getName());

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "employeeListDataProvider")
    public Object[][] employeeListDataProvider() {
        List<Employee> employeeList = Arrays.asList(
                Employee.builder().taxId("1").profession("Software Engineer").build(),
                Employee.builder().taxId("2").profession("BI").build(),
                Employee.builder().taxId("3").profession("QA").build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = employeeList;

        return data;
    }

    @Test(dataProvider = "employeeListDataProvider")
    public void testFindByTaxIdSuccess(List<Employee> employeeList) {
        EmployeeService employeeService = new EmployeeService(employeeList);
        String expectedTaxId = "1";
        String expectedProfession = "Software Engineer";

        Employee foundEmployee = employeeService.findByTaxId(expectedTaxId);

        Assert.assertEquals(employeeService.getEmployeeList(), employeeList);
        Assert.assertNotNull(foundEmployee);
        Assert.assertEquals(foundEmployee.getTaxId(), expectedTaxId);
        Assert.assertEquals(foundEmployee.getProfession(), expectedProfession);
    }

    @Test(dataProvider = "employeeListDataProvider")
    public void testFindByTaxIdFailNotFound(List<Employee> employeeList) {
        EmployeeService employeeService = new EmployeeService(employeeList);
        String expectedTaxId = "4";

        Employee foundEmployee = employeeService.findByTaxId(expectedTaxId);

        Assert.assertNull(foundEmployee);
    }

    @Test(expectedExceptions = NotGivenTaxIdException.class)
    public void testFindByTaxIdFailNotGivenTaxIdException() {
        EmployeeService employeeService = new EmployeeService(Collections.emptyList());

        employeeService.findByTaxId(null);
    }

    @Test
    public void testFindByTaxIdFailEmployeeListIsEmpty() {
        EmployeeService employeeService = new EmployeeService(Collections.emptyList());
        String expectedTaxId = "1";

        Employee foundEmployee = employeeService.findByTaxId(expectedTaxId);

        Assert.assertNull(foundEmployee);
    }
}