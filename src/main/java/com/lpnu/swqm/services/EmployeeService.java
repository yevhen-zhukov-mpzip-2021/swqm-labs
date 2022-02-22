package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Employee;
import com.lpnu.swqm.exceptions.NotGivenTaxIdException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class.getName());
    private List<Employee> employeeList;

    public Employee findByTaxId(String taxId) {
        if (StringUtils.isBlank(taxId)) {
            throw new NotGivenTaxIdException("taxId is blank! (null or empty string)");
        } else if (employeeList.isEmpty()) {
            log.warn("employee-list is empty");
            return null;
        }

        return employeeList.stream()
                .filter(employee -> employee.getTaxId().equals(taxId))
                .findFirst()
                .orElse(null);
    }
}
