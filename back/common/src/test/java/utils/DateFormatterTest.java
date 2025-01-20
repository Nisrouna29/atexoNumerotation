package utils;

import com.test.technique.utils.DateFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateFormatterTest {

    @Test
    void testFormat_WithValidDate_ReturnsFormattedDate() {
        // Arrange
        String dateFormat = "yyyy-MM-dd";
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        LocalDate date = LocalDate.of(2020, 5, 15);  // May 15, 2020

        // Act
        String result = dateFormatter.format(date);

        // Assert
        assertEquals("2020-05-15", result);  // Should format the date as "yyyy-MM-dd"
    }

    @Test
    void testFormat_WithDifferentDateFormat_ReturnsCorrectlyFormattedDate() {
        // Arrange
        String dateFormat = "MM/dd/yyyy";
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        LocalDate date = LocalDate.of(2020, 5, 15);  // May 15, 2020

        // Act
        String result = dateFormatter.format(date);

        // Assert
        assertEquals("05/15/2020", result);  // Should format the date as "MM/dd/yyyy"
    }


    @Test
    void testFormat_WithCustomDateFormat_ReturnsFormattedDate() {
        // Arrange
        String dateFormat = "dd-MM-yyyy";
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        LocalDate date = LocalDate.of(2020, 5, 15);  // May 15, 2020

        // Act
        String result = dateFormatter.format(date);

        // Assert
        assertEquals("15-05-2020", result);  // Should format the date as "dd-MM-yyyy"
    }

    @Test
    void testFormat_WithDatePatternWithoutLeadingZeroes() {
        // Arrange
        String dateFormat = "d/M/yyyy";  // Non-padded day and month
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        LocalDate date = LocalDate.of(2020, 5, 5);  // May 5, 2020

        // Act
        String result = dateFormatter.format(date);

        // Assert
        assertEquals("5/5/2020", result);  // Should format the date as "d/M/yyyy"
    }

    @Test
    void testFormat_WithZeroDateMonth_ReturnsPaddedDate() {
        // Arrange
        String dateFormat = "dd-MM-yyyy";  // Padded day and month
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        LocalDate date = LocalDate.of(2020, 1, 1);  // January 1, 2020

        // Act
        String result = dateFormatter.format(date);

        // Assert
        assertEquals("01-01-2020", result);  // Should format the date as "dd-MM-yyyy" with leading zeroes
    }
}

