package ru.darin.non_working_days.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.darin.non_working_days.util.DateResponse;
import ru.darin.non_working_days.util.DaysResponse;
import ru.darin.non_working_days.util.Months;
import ru.darin.non_working_days.util.YearResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NonWorkingDaysService {

    private final RestTemplate restTemplate;

    public NonWorkingDaysService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("non-working-days")
    public YearResponse getCommonResponseForYear(int year) {
        System.out.println("++++++++++++++++++++++");
        System.out.println("work service");
        System.out.println("++++++++++++++++++++++");
        String url = "https://xmlcalendar.ru/data/ru/" + year + "/calendar.json";
        return restTemplate.getForObject(url, YearResponse.class);
    }

    //1-st task
    public DaysResponse getCountOfNonWorkingDaysPerPeriod(String strDateFrom, String strDateTo) {
        Byte countOfDaysBetweenDates = 0;
        ZonedDateTime dateFrom = getZonedDateTimeFromStringDate(strDateFrom);
        ZonedDateTime dateTo = getZonedDateTimeFromStringDate(strDateTo);

        YearResponse response = getCommonResponseForYear(dateFrom.getYear());

        for (Months month : response.getMonths()) {
            String[] splittedDays = month.getDays().split(",");
            for (String str : splittedDays) {
                String strDay = str.replaceAll("[*]|[+]", "");
                ZonedDateTime currentDate = getZonedDateTimeFromStringForSingleDayOfCalendar(strDay, month.getMonth(), response.getYear());
                boolean isBetweenDates = dateFrom.isBefore(currentDate) && dateTo.isAfter(currentDate);
                if (isBetweenDates && str.matches("\\d{1,2}[^*]?")) countOfDaysBetweenDates++;
            }
        }
        return new DaysResponse(countOfDaysBetweenDates);
    }

    public static ZonedDateTime getZonedDateTimeFromStringDate(String strDate) {
        return ZonedDateTime.parse(strDate);
    }

    public static ZonedDateTime getZonedDateTimeFromStringForSingleDayOfCalendar(String day, String month, String year) {
        if (day.length() == 1 || (day.length() == 2 && (day.endsWith("+") || day.endsWith("*"))))
            day = "0".concat(day);
        if (month.length() == 1)
            month = "0".concat(month);

        String inputDateString = day + "-" + month + "-" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDateString, formatter);
        return date.atStartOfDay(ZoneId.of("Z"));
    }

    //2-d task
    // узнать какое будет число по истечении указанного числа рабочих дней (сегодняшний день не учитывать)
    public DateResponse getDateAfterCountOfWorkingDays(String countOfWorkDaysStr) {
        ZonedDateTime dateFrom = ZonedDateTime.now();
        ZonedDateTime dateAfterCount = dateFrom;
        dateAfterCount = dateAfterCount.plusDays(Integer.parseInt(countOfWorkDaysStr) + 1);

        YearResponse response = getCommonResponseForYear(ZonedDateTime.now().getYear());
        int monthIndex = dateFrom.getMonthValue() - 1;

        for (int i = monthIndex; i < response.getMonths().size(); i++) {
            String[] splittedDays = response.getMonths().get(i).getDays().split(",");
            for (String str : splittedDays) {
                String strDay = str.replaceAll("[*]|[+]", "");
                ZonedDateTime currentDate = getZonedDateTimeFromStringForSingleDayOfCalendar(strDay, response.getMonths().get(i).getMonth(), response.getYear());
                boolean isBetweenDates = dateFrom.isBefore(currentDate) && dateAfterCount.isAfter(currentDate);
                if (isBetweenDates && !str.contains("*")) dateAfterCount = dateAfterCount.plusDays(1);
            }
        }
        return new DateResponse(dateAfterCount.withZoneSameInstant(ZoneId.of("Z")));
    }
}
