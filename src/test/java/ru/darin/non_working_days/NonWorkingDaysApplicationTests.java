package ru.darin.non_working_days;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.darin.non_working_days.controller.Controller;
import ru.darin.non_working_days.dto.CountSearchDTO;
import ru.darin.non_working_days.dto.DateSearchDTO;
import ru.darin.non_working_days.dto.YearDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class NonWorkingDaysApplicationTests {

    @Autowired
    private Controller controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private CountSearchDTO countSearchDTO;

    private CountSearchDTO countSearchDTOWithWrongFormat;

    private DateSearchDTO dateSearchDTO;

    private YearDTO yearDTO;

    private DateSearchDTO dateSearchDTOWithWrongFormat;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @BeforeEach
    void setUp() {
        yearDTO = new YearDTO();
        yearDTO.setYear("2025");
        countSearchDTO = new CountSearchDTO();
        countSearchDTO.setCount("1");
        dateSearchDTO = new DateSearchDTO();
        dateSearchDTO.setDateFrom("2025-03-01T21:23:23Z");
        dateSearchDTO.setDateTo("2025-03-02T21:23:23Z");
    }

    @BeforeEach
    void setUpForThrowExceptions() {
        countSearchDTOWithWrongFormat = new CountSearchDTO();
        countSearchDTOWithWrongFormat.setCount("wrong");
        dateSearchDTOWithWrongFormat = new DateSearchDTO();
        dateSearchDTOWithWrongFormat.setDateFrom("2025-03-01T21:23:23Wrong");
        dateSearchDTOWithWrongFormat.setDateTo("2025-03-02T21:23:23Z");
    }

    @Test
    void getCommonInfoAboutYear() throws Exception {
        this.mockMvc
                .perform(post("/showInfo")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(yearDTO)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andExpect(jsonPath("months", Matchers.hasSize(12)));
    }

    @Test
    void getCountOfNonWorkingDaysBetweenDays() throws Exception {
        this.mockMvc
                .perform(post("/countOfDays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(dateSearchDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getDateAfterCountOfWorkingDays() throws Exception {
        this.mockMvc
                .perform(post("/dateAfterCount")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(countSearchDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getCountOfNonWorkingDaysBetweenDaysWithThrowException() throws Exception {
        this.mockMvc
                .perform(post("/countOfDays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(dateSearchDTOWithWrongFormat)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getDateAfterCountOfWorkingDaysWithThrowException() throws Exception {
        this.mockMvc
                .perform(get("/dateAfterCount")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(countSearchDTOWithWrongFormat)))
                .andExpect(status().is4xxClientError());
    }

}
