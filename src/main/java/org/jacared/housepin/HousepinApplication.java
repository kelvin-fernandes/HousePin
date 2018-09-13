package org.jacared.housepin;

import org.jacared.housepin.models.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HousepinApplication {
	public static void main(String[] args) {
		SpringApplication.run(HousepinApplication.class, args);
	}
}
