package com.purerangers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public static void main(String[] args) throws ParseException {
        //"9/21/1982"
        formatDate("21/9/1982");
    }
    private static LocalDate formatDate(String s) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date = LocalDate.parse(s, formatter);
        System.out.println("Date is: " + date); //
        return date;
    }
}
