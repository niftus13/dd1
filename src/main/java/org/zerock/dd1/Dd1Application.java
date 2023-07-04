package org.zerock.dd1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Dd1Application {

	public static void main(String[] args) {
		SpringApplication.run(Dd1Application.class, args);
	}

}
