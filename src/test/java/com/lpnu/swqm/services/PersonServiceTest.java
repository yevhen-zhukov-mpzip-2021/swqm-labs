package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Person;
import com.lpnu.swqm.exceptions.IncorrectPersonAgeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class PersonServiceTest {

    private static final Logger log = LogManager.getLogger(PersonServiceTest.class.getName());

    private final PersonService personService = new PersonService();

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "personDataProvider")
    public Object[][] personDataProvider() {
        return new Object[][]{
                {new Person(
                        LocalDateTime.of(1982, 2, 1, 11, 45),
                        "Yevhen",
                        "Zhukov")}
        };
    }

    @DataProvider(name = "notAdultPersonDataProvider")
    public Object[][] notAdultPersonDataProvider() {
        return new Object[][]{
                {new Person(
                        LocalDateTime.of(2022, 2, 1, 11, 45),
                        "John",
                        "Doe")}
        };
    }

    @DataProvider(name = "notBornYetPersonDataProvider")
    public Object[][] notBornYetPersonDataProvider() {
        return new Object[][]{
                {new Person(
                        LocalDateTime.of(2222, 2, 1, 11, 45),
                        "Yevhen",
                        "Zhukov")}
        };
    }

    @DataProvider(name = "nullPersonDataProvider")
    public Object[][] nullPersonDataProvider() {
        return new Object[][]{
                {null}
        };
    }

    @Test(dataProvider = "personDataProvider")
    public void testGetAge(Person person) {

        long actual = personService.getAge(person);
        long expected = 40;

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "personDataProvider")
    public void testName(Person person) {

        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        final String NAME_PATTERN = "(^[A-Z][a-z]{3,})((-[A-Z][a-z]{3,})+)?";

        Assert.assertNotNull(firstName);
        Assert.assertNotNull(lastName);
        Assert.assertTrue(firstName.matches(NAME_PATTERN));
        Assert.assertTrue(lastName.matches(NAME_PATTERN));
    }

    @Test(dataProvider = "personDataProvider", dependsOnMethods = "testGetAge")
    public void testIsAdult(Person person) {

        boolean isAdultPerson = personService.isAdult(person);

        Assert.assertTrue(isAdultPerson);
    }

    @Test(dataProvider = "notAdultPersonDataProvider")
    public void testIsAdultFalse(Person person) {

        boolean isAdultPerson = personService.isAdult(person);

        Assert.assertFalse(isAdultPerson);
    }

    @Test(dataProvider = "nullPersonDataProvider")
    public void testGetAgeNullPerson(Person person) {

        long actual = personService.getAge(person);
        long expected = 0;

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "nullPersonDataProvider")
    public void testIsAdultNullPerson(Person person) {

        boolean isAdultPerson = personService.isAdult(person);

        Assert.assertFalse(isAdultPerson);
    }

    @Test(dataProvider = "notBornYetPersonDataProvider", expectedExceptions = IncorrectPersonAgeException.class)
    public void testNotBornYetPersonDataProvider(Person person) {

        personService.getAge(person);
    }
}