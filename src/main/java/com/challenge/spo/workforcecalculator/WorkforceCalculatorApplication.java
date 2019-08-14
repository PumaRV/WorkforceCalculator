package com.challenge.spo.workforcecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WorkforceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkforceCalculatorApplication.class, args);
	}

}
