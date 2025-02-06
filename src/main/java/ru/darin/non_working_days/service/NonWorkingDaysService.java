package ru.darin.non_working_days.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.darin.non_working_days.util.Months;
import ru.darin.non_working_days.util.YearResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public class NonWorkingDaysService {

    private final RestTemplate restTemplate;

    public NonWorkingDaysService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("non-working-days")
    public YearResponse getCommonResponseForYear(int year){
        System.out.println("Работа сервиса");
        String url = "https://xmlcalendar.ru/data/ru/" + year + "/calendar.json";
        YearResponse response = restTemplate.getForObject(url, YearResponse.class);
//        System.out.println(response.getYear() + " <- year --> " + response.getMonths().get(2).getDays());
        List<Months> months = response.getMonths();
        for (Months month : months){
            System.out.println(month.getMonth() + " <--- номер месяца");

        }
        return response;
    }

    //1-st task
    public Integer getCountOfNonWorkingDaysPerPeriod(String dateFrom, String dateTo){
        //TODO:
        // - сперва необходимо с помощью Split по символу ',' разбить строку "days" и получить массив
        // 1) ввожу диапазон дат: 2020-11-16T04:25:03Z   -   2020-11-28T04:25:03Z
        // 2) извлекаю месяц и день 'ОТ' а также месяц и день 'ДО'
        // 3) прохожусь по YearResponse, нахожу нужный год, месяц, ближайшее число 'ОТ' в большую сторону
        // - например, когда указываем начальный диапазон с рабочего дня (его не будет в наших данных выходных дней)
        // 4) иду и считаю количество выходных дней до тех пор, пока не дойду до конечной даты
        // 5) вывожу число
        return null;
    }

    //2-d task
    public LocalDate getDateAfterCountOfWorkingDays(){
        return LocalDate.now();
    }
}
