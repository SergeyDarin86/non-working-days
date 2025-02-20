package ru.darin.non_working_days.dto;

import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@ValidSearchDTO
public class CountSearchDTO {

    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountSearchDTO{" +
                "count='" + count + '\'' +
                '}';
    }
}
