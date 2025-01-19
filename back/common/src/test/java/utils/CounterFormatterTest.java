package utils;

import com.test.technique.utils.CounterFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterFormatterTest {

    @Test
    void testFormat_WithLength5_ReturnsFormattedNumberWithLeadingZeros() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(5);

        // Act
        String result = counterFormatter.format(42);

        // Assert
        assertEquals("00042", result); // Number should be formatted as "00042"
    }

    @Test
    void testFormat_WithLength3_ReturnsFormattedNumberWithLeadingZeros() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(3);

        // Act
        String result = counterFormatter.format(7);

        // Assert
        assertEquals("007", result); // Number should be formatted as "007"
    }

    @Test
    void testFormat_WithLength4_ReturnsFormattedNumberAndTruncate() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(4);

        // Act
        String result = counterFormatter.format(12345);

        // Assert
        assertEquals("1234", result); // Number should be truncated to "1234"
    }

    @Test
    void testFormat_WithLength2_ReturnsTruncatedNumber() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(2);

        // Act
        String result = counterFormatter.format(100);

        // Assert
        assertEquals("10", result); // Since the number has 3 digits, it should be truncated to "100"
    }

    @Test
    void testFormat_WithLength1_ReturnsTruncatedSingleDigitNumber() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(1);

        // Act
        String result = counterFormatter.format(9);

        // Assert
        assertEquals("9", result); // Since the number is less than 10, it should be returned as "9"
    }

    @Test
    void testFormat_WithZeroLength_ReturnsEmptyString() {
        // Arrange
        CounterFormatter counterFormatter = new CounterFormatter(0);

        // Act
        String result = counterFormatter.format(123);

        // Assert
        assertEquals("", result); // If the length is 0, it should return an empty string.
    }
}
