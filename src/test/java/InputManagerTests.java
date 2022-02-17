import com.purerangers.InputManager;
import com.purerangers.TextToNumberConverter;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class InputManagerTests {
    private String input;
    private String expectedResult;

    public InputManagerTests(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                { "one" , "1 months"},
                { "twelve", "12 months"},
                { "thirty two", "32 months"},
                { "three hundred", "300 months"},
                { "four hundred and twenty", "420 months"},
                { "six hundred and fifty two", "652 months"},
                { "one thousand and twenty two", "1022 months"},
                { "234", "234 months"},
                { "three 23", "3 months"},
                { "Twenty one months", "21 months"},
                { "twenty-four weeks", "6 months"},
                { "twenty-four years.", "288 months"},
        });
    }
    @Test
    public void convertTextualNumbersToMonths() {
        assertEquals(expectedResult, InputManager.convertTextualNumbersInString(input));
    }
}
