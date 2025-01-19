package utils;

import com.test.technique.utils.NameFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameFormatterTest {

    private NameFormatter nameFormatter;

    @BeforeEach
    void setUp() {
        nameFormatter = new NameFormatter(5); // Set default length to 5 for tests
    }

    @Test
    void format_InputLongerThanLength_TruncatesString() {
        // Arrange
        String input = "Jonathan";

        // Act
        String result = nameFormatter.format(input);

        // Assert
        assertEquals("Jonat", result); // Input should be truncated to 5 characters
    }

    @Test
    void format_InputShorterThanLength_PadsWithUnderscores() {
        // Arrange
        String input = "Jo";

        // Act
        String result = nameFormatter.format(input);

        // Assert
        assertEquals("Jo___", result); // Input should be padded with underscores to make it length 5
    }

    @Test
    void format_InputExactlyEqualToLength_ReturnsInputAsIs() {
        // Arrange
        String input = "Alice";

        // Act
        String result = nameFormatter.format(input);

        // Assert
        assertEquals("Alice", result); // Input should remain unchanged
    }

    @Test
    void format_LengthZero_ReturnsEmptyString() {
        // Arrange
        NameFormatter nameFormatterZeroLength = new NameFormatter(0); // Set length to 0
        String input = "John";

        // Act
        String result = nameFormatterZeroLength.format(input);

        // Assert
        assertEquals("", result); // Length 0 should return an empty string
    }

    @Test
    void format_EmptyInput_ReturnsPaddedString() {
        // Arrange
        String input = "";
        NameFormatter nameFormatterFiveLength = new NameFormatter(5);

        // Act
        String result = nameFormatterFiveLength.format(input);

        // Assert
        assertEquals("_____", result); // Empty string should be padded with underscores to make it length 5
    }

    @Test
    void format_SpecialCharacters_ReturnsProperlyFormattedString() {
        // Arrange
        String input = "$#%";

        // Act
        String result = nameFormatter.format(input);

        // Assert
        assertEquals("$#%__" , result); // Special characters should be padded with underscores
    }
}
