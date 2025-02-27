package ru.darin.non_working_days.service;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class NonWorkingDaysService {

    //TODO:
    // возможно добавить исключение для метода showInfo
    // кэш - правильно ли работает?
    // логирование +
    // тестирование +
    // докер +
    // документация
    // readme.md
    private final RestTemplate restTemplate;

    public NonWorkingDaysService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("non-working-days")
    public YearResponse getCommonResponseForYear(String year) {
        log.info("Start method getCommonResponseForYear(year) for NonWorkingDaysService, year is: {} ", year);
        String url = "https://xmlcalendar.ru/data/ru/" + year + "/calendar.json";
        return restTemplate.getForObject(url, YearResponse.class);
    }

    public DaysResponse getCountOfNonWorkingDaysPerPeriod(String strDateFrom, String strDateTo) {
        log.info("Start method getCountOfNonWorkingDaysPerPeriod(strDateFrom, strDateTo) for NonWorkingDaysService, strDateFrom is: {}, strDateTo is: {} ", strDateFrom, strDateTo);
        Byte countOfDaysBetweenDates = 0;
        ZonedDateTime dateFrom = getZonedDateTimeFromStringDate(strDateFrom);
        ZonedDateTime dateTo = getZonedDateTimeFromStringDate(strDateTo);

        YearResponse response = getCommonResponseForYear(String.valueOf(dateFrom.getYear()));

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

    public ZonedDateTime getZonedDateTimeFromStringDate(String strDate) {
        log.info("Start method getZonedDateTimeFromStringDate(strDate) for NonWorkingDaysService, strDate is: {} ", strDate);
        return ZonedDateTime.parse(strDate);
    }

    public ZonedDateTime getZonedDateTimeFromStringForSingleDayOfCalendar(String day, String month, String year) {
        log.info("Start method getZonedDateTimeFromStringForSingleDayOfCalendar(day, month, year) for NonWorkingDaysService, day is: {}, month is: {}, year is: {} ", day, month, year);
        if (day.length() == 1 || (day.length() == 2 && (day.endsWith("+") || day.endsWith("*"))))
            day = "0".concat(day);
        if (month.length() == 1)
            month = "0".concat(month);

        String inputDateString = day + "-" + month + "-" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputDateString, formatter);
        return date.atStartOfDay(ZoneId.of("Z"));
    }

    public DateResponse getDateAfterCountOfWorkingDays(String countOfWorkDaysStr) {
        log.info("Start method getDateAfterCountOfWorkingDays(countOfWorkDaysStr) for NonWorkingDaysService, countOfWorkDaysStr is: {} ", countOfWorkDaysStr);

        ZonedDateTime dateFrom = ZonedDateTime.now();
        ZonedDateTime dateAfterCount = dateFrom;
        dateAfterCount = dateAfterCount.plusDays(Integer.parseInt(countOfWorkDaysStr) + 1);

        YearResponse response = getCommonResponseForYear(String.valueOf(ZonedDateTime.now().getYear()));
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
        log.info("Finish method getDateAfterCountOfWorkingDays(countOfWorkDaysStr) for NonWorkingDaysService, dateAfterCount is: {} ", dateAfterCount);
        return new DateResponse(dateAfterCount.withZoneSameInstant(ZoneId.of("Z")));
    }
}
