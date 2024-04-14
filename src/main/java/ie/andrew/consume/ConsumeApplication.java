package ie.andrew.consume;

import com.fasterxml.jackson.databind.JsonNode;
import ie.andrew.consume.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootApplication
public class ConsumeApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumeApplication.class, args);
	}

	@Bean
	public static RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Value( "${security.username}" )
	private String userName;

	@Value( "${security.password}" )
	private String password;

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {

			System.out.println("username: " + userName);
			System.out.println("password: " + password);

		};
	}
}