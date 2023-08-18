package org.example.resources;

import org.testng.annotations.DataProvider;
public class TestDataProvider {
    @DataProvider(name = "validBirthDates")
    public static Object[][] validBirthDates() {
        return new Object[][] {
                { "1994-08-15", 29 },
                { "1981-06-03", 42 },
                { "2008-03-19", 15 }
        };
    }

    @DataProvider(name = "invalidBirthDates")
    public Object[][] invalidBirthDates() {
        return new Object[][] {
                { "1990/05/15" }, // Invalid format
                { "abc" },        // Invalid format
        };
    }
}
