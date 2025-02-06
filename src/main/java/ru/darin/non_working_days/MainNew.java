package ru.darin.non_working_days;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MainNew {

    static final String STRING_DATE_fROM = "2021-01-21T21:23:23Z"; //01-28

    static final String STRING_DATE_TO = "2021-01-29T20:23:23Z";   //02-08

    public static ZonedDateTime splitDate(String stringDate) {
        return ZonedDateTime.parse(stringDate);
    }

    public static void main(String[] args) {
        ZonedDateTime dateFrom = splitDate(STRING_DATE_fROM);
        int day = 22;
        String month = "1";
        String year = "2022";
        ZonedDateTime currentDate = translateStringFromSingleDayToDate(day,month,year);
        System.out.println(currentDate + " | " + dateFrom + " | " + currentDate.isAfter(dateFrom));

    }
    public static ZonedDateTime translateStringFromSingleDayToDate(int day, String month, String year){
        String inputDateString = day + "-0" + month + "-" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDateString, formatter);
        return date.atStartOfDay(ZoneId.of("Z"));
    }
}
