package utils;

import net.datafaker.Faker;

public class DataFakerUtil {
    private static final Faker faker = new Faker();

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomPassword() {
        return faker.internet().password(8, 16);
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }
    public static String getRandomZipCode() {
        return faker.address().zipCode();
    }
}

