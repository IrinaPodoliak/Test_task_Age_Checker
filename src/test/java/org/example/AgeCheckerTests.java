package org.example;

import org.example.util.TestDataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import static org.example.util.TextMessages.TestMessages.*;
import static org.testng.Assert.assertEquals;

public class AgeCheckerTests {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "validBirthDates")
    public void testAgeCalculation(String birthDateInput, int expectedAge) {
        LocalDate birthDate = LocalDate.parse(birthDateInput);
        int calculatedAge = AgeChecker.calculateAge(birthDate);
        assertEquals(calculatedAge, expectedAge);
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "validBirthDates")
    public void testUserIsAdult(String birthDateInput, int expectedAge) {
        LocalDate birthDate = LocalDate.parse(birthDateInput);
        String result = AgeChecker.checkAge(birthDate);
        if (expectedAge >= 18) {
            assertEquals(result, ADULT_MESSAGE);
        } else {
            assertEquals(result, NOT_ADULT_MESSAGE);
        }
    }

    @Test
    public void testUserIsYoungerThan18() {
        LocalDate birthDate = LocalDate.now().minusYears(16);
        String result = AgeChecker.checkAge(birthDate);

        assertEquals(result, NOT_ADULT_MESSAGE);
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidBirthDates", expectedExceptions = DateTimeParseException.class)
    public void testInvalidInputFormat(String invalidBirthDateInput) {
        LocalDate birthDate = LocalDate.parse(invalidBirthDateInput);
        AgeChecker.checkAge(birthDate);
    }

    @Test
    public void testFutureDate() {
        LocalDate futureBirthDate = LocalDate.now().plusDays(1);
        String result = AgeChecker.checkAge(futureBirthDate);

        assertEquals(result, FUTURE_DATE_MESSAGE);
    }

    @Test
    public void testExact18YearsOld() {
        LocalDate birthDate = LocalDate.now().minusYears(18);
        String result = AgeChecker.checkAge(birthDate);

        assertEquals(result, ADULT_MESSAGE);
    }

    @Test
    public void testAlmost18YearsOld() {
        LocalDate birthDate = LocalDate.now().minus(Period.ofYears(18).minusDays(1));
        String result = AgeChecker.checkAge(birthDate);

        assertEquals(result, NOT_ADULT_MESSAGE);
    }
}
