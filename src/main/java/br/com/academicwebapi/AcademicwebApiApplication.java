package br.com.academicwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class AcademicwebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicwebApiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
