package com.lpnu.swqm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Person {

    private LocalDateTime bornDate;
    private String firstName;
    private String lastName;
}
