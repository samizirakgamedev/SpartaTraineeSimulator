package purerangers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static com.purerangers.DateFormat.formatDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFormatTest {
    @Test
    @DisplayName("Format Date:Invalid String")
    public void checkInvalidStringToDate() throws ParseException
    {
        String s="hello";
        formatDate(s);
        LocalDate expected= null;
        LocalDate actual= formatDate(s);
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Format Date: Valid String")
    public void checkValidStringToDate() throws ParseException
    {
        String s="21/9/1982";
        formatDate(s);
        LocalDate actual= formatDate(s);
        LocalDate expected = LocalDate.of(1982, 9, 21);
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Format Date: Checks null input formatDate")
    public void checkNullStringToDate() throws ParseException
    {
        String s=null;
        formatDate(s);
        LocalDate actual= formatDate(s);
        LocalDate expected = null;
        assertEquals(expected,actual);
    }
}
