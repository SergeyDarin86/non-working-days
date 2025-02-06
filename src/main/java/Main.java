import ru.darin.non_working_days.util.Months;
import ru.darin.non_working_days.util.YearResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //эти поля будут в SearchDTO
    static final String STRING_DATE_fROM = "2021-01-21T21:23:23Z"; //01-28
    static final String STRING_DATE_TO = "2021-12-18T20:23:23Z";   //02-08
    //

    public static ZonedDateTime splitDate(String stringDate) {
        return ZonedDateTime.parse(stringDate);
    }

    public static void main(String[] args) {

        ZonedDateTime dateFrom = splitDate(STRING_DATE_fROM);
        ZonedDateTime dateTo = splitDate(STRING_DATE_TO);
//
        YearResponse response = new YearResponse();
        response.setYear("2021");
//
        Months months1 = new Months();
        months1.setMonth("1");
        months1.setDays("1,2+,3*,14*,15,21,22,28*,29*");
//
        Months months2 = new Months();
        months2.setMonth("12");
        months2.setDays("1*,2+,3,11,12,18,19,22*,23,24+,25,26");
//
        List<Months> monthsList = new ArrayList<>();
        monthsList.add(months1);
        monthsList.add(months2);
//
        response.setMonths(monthsList);
        int countOfDaysBetweenDates = 0;

        for (Months month : response.getMonths()) {
            System.out.println(month.getDays());
            String[] splittedDays = month.getDays().split(",");
            for (String str : splittedDays) {
                String strNew = str.replaceAll("[*]|[+]", "");
                if (strNew.length() == 1 || (strNew.length() == 2 && (strNew.endsWith("+") || strNew.endsWith("*"))))
                    strNew = "0".concat(strNew);

                String monthFromCalendar = month.getMonth();
                if (monthFromCalendar.length() == 1) monthFromCalendar = "0".concat(monthFromCalendar);

                ZonedDateTime currentDate = getZonedDateTimeFromStringForSingleDayOfCalendar(strNew, monthFromCalendar, response.getYear());
                boolean isBetweenDates = dateFrom.isBefore(currentDate) && dateTo.isAfter(currentDate);
                if (isBetweenDates && str.matches("\\d{1,2}[^*]?")) countOfDaysBetweenDates++;
            }
//            countOfAllDays += splittedDays.length;
//            countOfNonWorkingDays += Arrays.stream(splittedDays).distinct().filter(s -> s.matches("\\d{1,2}[^*]?")).count();
        }

        System.out.println();
        System.out.println(countOfDaysBetweenDates + " - выходных дней между датами: " +
                dateFrom + " и " + dateTo);

    }

    public static ZonedDateTime getZonedDateTimeFromStringForSingleDayOfCalendar(String day, String month, String year) {
        String inputDateString = day + "-" + month + "-" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDateString, formatter);
        return date.atStartOfDay(ZoneId.of("Z"));
    }
}
