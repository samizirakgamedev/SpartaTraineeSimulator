package purerangers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static com.purerangers.DateFormat.formatDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomNumberGeneratorTest {
    @Test
    @DisplayName("Check Distribution of Random number generator")
    public void checkValidStringToDate() throws ParseException
    {
        String s="21/9/1982";
        formatDate(s);
        LocalDate actual= formatDate(s);
        LocalDate expected = LocalDate.of(1982, 9, 21);
        assertEquals(expected,actual);
    }
}
