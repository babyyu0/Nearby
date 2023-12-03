package com.ssafy.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Pair01ParkSeungSooYouYoungApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pair01ParkSeungSooYouYoungApplication.class, args);
	}

}
