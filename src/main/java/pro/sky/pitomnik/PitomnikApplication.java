package pro.sky.pitomnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
@EnableScheduling
public class PitomnikApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitomnikApplication.class, args);
	}

}
