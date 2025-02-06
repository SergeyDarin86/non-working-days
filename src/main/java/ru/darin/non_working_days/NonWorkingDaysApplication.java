package ru.darin.non_working_days;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NonWorkingDaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonWorkingDaysApplication.class, args);
	}

}
