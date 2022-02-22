package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Employee extends Person {

    private final String taxId;
    private final String profession;

    @Builder
    public Employee(LocalDateTime bornDate, String firstName, String lastName, String taxId, String profession) {
        super(bornDate, firstName, lastName);
        this.taxId = taxId;
        this.profession = profession;
    }
}
