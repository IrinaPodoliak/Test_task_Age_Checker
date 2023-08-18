package org.example.resources;

public class DataProvider {
    @DataProvider(name = "validBirthDates")
    public static Object[][] validBirthDates() {
        return new Object[][] {
                { "1994-08-15", 29 },
                { "1981-06-03", 42 },
                { "2008-03-19", 15 }
        };
    }

    @org.testng.annotations.DataProvider(name = "invalidBirthDates")
    public Object[][] invalidBirthDates() {
        return new Object[][] {
                { "1990/05/15" }, // Invalid format
                { "abc" },        // Invalid format
                { "2023-08-18" }  // Future date
        };
    }
}
