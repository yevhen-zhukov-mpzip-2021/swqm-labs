package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.CompanyEmployee;
import com.lpnu.swqm.exceptions.CompanyDepartmentNotGivenException;
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

public class CompanyEmployeeServiceTest {

    private static final Logger log = LogManager.getLogger(CompanyEmployeeServiceTest.class.getName());

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "companyEmployeesDataProvider")
    public Object[][] companyEmployeesDataProvider() {
        List<CompanyEmployee> companyEmployees = Arrays.asList(
                CompanyEmployee.companyBuilder().department(CompanyEmployee.Department.BACK_OFFICE).build(),
                CompanyEmployee.companyBuilder().department(CompanyEmployee.Department.FRONT_OFFICE).build(),
                CompanyEmployee.companyBuilder().department(CompanyEmployee.Department.BACK_OFFICE).build()
        );

        Object[][] data = new Object[1][1];
        data[0][0] = companyEmployees;

        return data;
    }

    @Test(dataProvider = "companyEmployeesDataProvider")
    public void testFindByDepartmentSuccess(List<CompanyEmployee> companyEmployees) {
        CompanyEmployeeService companyEmployeeService = new CompanyEmployeeService();
        List<CompanyEmployee> filteredByDepartment = companyEmployeeService.findByDepartment(
                companyEmployees, CompanyEmployee.Department.BACK_OFFICE);

        Assert.assertFalse(filteredByDepartment.isEmpty());
        Assert.assertEquals(filteredByDepartment.size(), 2);
    }

    @Test(expectedExceptions = CompanyDepartmentNotGivenException.class)
    public void testFindByDepartmentFailDepartmentIsNull() {
        CompanyEmployeeService companyEmployeeService = new CompanyEmployeeService();
        companyEmployeeService.findByDepartment(null, null);
    }

    @Test
    public void testFindByDepartmentFailEmployeesNotGiven() {
        CompanyEmployeeService companyEmployeeService = new CompanyEmployeeService();
        List<CompanyEmployee> filteredByDepartment = companyEmployeeService.findByDepartment(
                Collections.emptyList(), CompanyEmployee.Department.FRONT_OFFICE);

        Assert.assertTrue(filteredByDepartment.isEmpty());
    }
}