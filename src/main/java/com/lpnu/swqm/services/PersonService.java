package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Person;
import com.lpnu.swqm.exceptions.IncorrectPersonAgeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PersonService {
    private static final Logger log = LogManager.getLogger(PersonService.class.getName());

    private static final int ADULT_AGE = 18;

    public long getAge(Person person) {
        if (Objects.isNull(person)) {
            log.warn("Null Person instance passed");

            return 0;
        }

        LocalDateTime bornDate = person.getBornDate();
        LocalDateTime currentDate = LocalDateTime.now();
        long age = ChronoUnit.YEARS.between(bornDate, currentDate);

        if (age < 0) {
            throw new IncorrectPersonAgeException("Incorrect Person Born Date passed! It should not be in future!");
        }

        return age;
    }

    public boolean isAdult(Person person) {
        if (Objects.isNull(person)) {
            log.warn("Null Person instance passed");

            return false;
        }

        long personAge = getAge(person);

        return personAge >= ADULT_AGE;
    }
}
