package hellowoori.backendproproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendProProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendProProjectApplication.class, args);
	}

}
