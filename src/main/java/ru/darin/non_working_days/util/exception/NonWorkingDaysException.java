package ru.darin.non_working_days.util.exception;

public class NonWorkingDaysException extends RuntimeException{
    public NonWorkingDaysException(String errorMsg){
        super(errorMsg);
    }
}
