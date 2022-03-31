package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CompanyEmployee extends Employee {

    private final Department department;

    @Builder(builderMethodName = "companyBuilder")
    public CompanyEmployee(LocalDateTime bornDate, String firstName, String lastName, String taxId, String profession, Department department) {
        super(bornDate, firstName, lastName, taxId, profession);
        this.department = department;
    }

    public enum Department {
        FRONT_OFFICE, BACK_OFFICE
    }
}
