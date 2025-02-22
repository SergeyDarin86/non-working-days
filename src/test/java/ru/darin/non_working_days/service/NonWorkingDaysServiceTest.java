package ru.darin.non_working_days.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import ru.darin.non_working_days.util.YearResponse;

import static org.junit.jupiter.api.Assertions.*;
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
    }

    @Test
    void getZonedDateTimeFromStringForSingleDayOfCalendar() {
    }

    @Test
    void getDateAfterCountOfWorkingDays() {
    }
}