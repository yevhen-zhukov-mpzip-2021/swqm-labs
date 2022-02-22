package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.CompanyEmployee;
import com.lpnu.swqm.exceptions.CompanyDepartmentNotGivenException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class CompanyEmployeeService {

    private static final Logger log = LogManager.getLogger(CompanyEmployeeService.class.getName());

    public List<CompanyEmployee> findByDepartment(List<CompanyEmployee> companyEmployees,
                                                  CompanyEmployee.Department department) {
        if (Objects.isNull(department)) {
            throw new CompanyDepartmentNotGivenException("Min price-list value should be less, than Max");
        } else if (companyEmployees.isEmpty()) {
            log.warn("Price-list is empty");
            return Collections.emptyList();
        }

        return companyEmployees.stream()
                .filter(companyEmployee -> companyEmployee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }
}
