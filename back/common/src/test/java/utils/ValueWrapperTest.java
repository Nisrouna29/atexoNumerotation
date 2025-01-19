package utils;

import com.test.technique.utils.ValueWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueWrapperTest {

    private ValueWrapper valueWrapper;

    @BeforeEach
    void setUp() {
        valueWrapper = new ValueWrapper();
    }

    @Test
    void wrap_AllValuesNonNull_ReturnsConcatenatedUppercaseString() {
        // Arrange
        String value = "value";
        String prefix = "PRE-";
        String suffix = "-SUF";

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("PRE-VALUE-SUF", result);
    }

    @Test
    void wrap_ValueIsNull_ReturnsPrefixAndSuffixUppercase() {
        // Arrange
        String value = null;
        String prefix = "PRE-";
        String suffix = "-SUF";

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("PRE--SUF", result);
    }

    @Test
    void wrap_PrefixIsNull_ReturnsValueAndSuffixUppercase() {
        // Arrange
        String value = "value";
        String prefix = null;
        String suffix = "-SUF";

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("VALUE-SUF", result);
    }

    @Test
    void wrap_SuffixIsNull_ReturnsPrefixAndValueUppercase() {
        // Arrange
        String value = "value";
        String prefix = "PRE-";
        String suffix = null;

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("PRE-VALUE", result);
    }

    @Test
    void wrap_AllValuesNull_ReturnsEmptyString() {
        // Arrange
        String value = null;
        String prefix = null;
        String suffix = null;

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("", result);
    }

    @Test
    void wrap_ValueIsEmpty_ReturnsPrefixAndSuffixUppercase() {
        // Arrange
        String value = "";
        String prefix = "PRE-";
        String suffix = "-SUF";

        // Act
        String result = valueWrapper.wrap(value, prefix, suffix);

        // Assert
        assertEquals("PRE--SUF", result);
    }
}
