package utils;

import static org.assertj.core.api.Assertions.assertThat;



public class AssertionHelper {

    public static void assertTextContains(String actual, String expected) {
        assertThat(actual)
                .withFailMessage("Expected text to contain: '%s' but was: '%s'", expected, actual)
                .contains(expected);
    }

    public static void assertEqualsIgnoreCase(String actual, String expected) {
        assertThat(actual)
                .withFailMessage("Expected '%s' to equal (ignore case): '%s'", actual, expected)
                .isEqualToIgnoringCase(expected);
    }

    public static void assertExactText(String actual, String expected) {
        assertThat(actual)
                .withFailMessage("Expected exact text: '%s' but got: '%s'", expected, actual)
                .isEqualTo(expected);
    }
}