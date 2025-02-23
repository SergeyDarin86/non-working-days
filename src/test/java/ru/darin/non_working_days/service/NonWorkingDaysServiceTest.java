package ru.darin.non_working_days.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import ru.darin.non_working_days.util.YearResponse;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class NonWorkingDaysServiceTest {

    private YearResponse yearResponse;

    private RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    @InjectMocks
    private NonWorkingDaysService service;

    @BeforeEach
    public void setUp() {
        String url = "http://example-url.com";
        MockitoAnnotations.openMocks(this);
        yearResponse = restTemplate.getForObject(url, YearResponse.class);
    }

    @Test
    void getCommonResponseForYear() {
        service.getCommonResponseForYear(2003);
        when(restTemplate.getForObject(anyString(), Mockito.eq(YearResponse.class))).thenReturn(yearResponse);
    }

    @Test
    void getCountOfNonWorkingDaysPerPeriod() {
    }

    @Test
    void getZonedDateTimeFromStringDate() {
        String strDate = "2025-02-11T00:00:00Z";
        assertEquals(ZonedDateTime.of(2025, 02, 11, 00, 00, 00, 0, ZoneId.of("Z")), service.getZonedDateTimeFromStringDate(strDate));
    }

    @Test
    void getZonedDateTimeFromStringForSingleDayOfCalendar() {
        String day = "1";
        String month = "1";
        String year = "2025";
        assertEquals(ZonedDateTime.of(2025, 1, 1, 00, 00, 00, 0, ZoneId.of("Z")),service.getZonedDateTimeFromStringForSingleDayOfCalendar(day,month,year));
    }

    @Test
    void getDateAfterCountOfWorkingDays() {
    }
}