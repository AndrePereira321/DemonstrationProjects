package org.goforfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoForFoodApplication {

	public static final String HOME_PAGE = "/";

	public static void main(String[] args) {
		SpringApplication.run(GoForFoodApplication.class, args);
	}

}
