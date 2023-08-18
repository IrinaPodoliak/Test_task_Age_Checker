package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.example.resources.TextMessages.TestMessages.*;

public class AgeCalculator {
    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }

    public static String checkAge(LocalDate birthDate) {
        if (birthDate.isAfter(LocalDate.now())) {
            return FUTURE_DATE_MESSAGE;
        }

        int age = calculateAge(birthDate);

        if (age >= 18) {
            return ADULT_MESSAGE;
        } else {
            return NOT_ADULT_MESSAGE;
        }
    }

    public static void main(String[] args) {
        System.out.print(ENTER_BIRTHDATE_MESSAGE);
        try (Scanner scanner = new Scanner(System.in)) {
            String birthDateInput = scanner.nextLine();

            try {
                LocalDate birthDate = LocalDate.parse(birthDateInput);
                String result = checkAge(birthDate);
                System.out.println(result);

            } catch (DateTimeParseException e) {
                System.out.println(INVALID_FORMAT_MESSAGE);
            }
        }
    }
}
