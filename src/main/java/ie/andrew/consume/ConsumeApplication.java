package ie.andrew.consume;

import com.fasterxml.jackson.databind.JsonNode;
import ie.andrew.consume.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			/*Quote quote = restTemplate.getForObject(
					"http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());


			JsonNode quote = restTemplate.getForObject(
					"http://localhost:8080/api", JsonNode.class);
			log.info(quote.toString());*/

			ArrayList<Quote> quotes = new ArrayList<>();

			quotes.add(restTemplate.getForObject(
					"https://jsonplaceholder.typicode.com/posts/1", Quote.class));

			quotes.add(restTemplate.getForObject(
					"https://jsonplaceholder.typicode.com/posts/2", Quote.class));

			quotes.add(restTemplate.getForObject(
					"https://jsonplaceholder.typicode.com/posts/3", Quote.class));

			quotes.add(restTemplate.getForObject(
					"https://jsonplaceholder.typicode.com/posts/4", Quote.class));


			quotes.forEach(quote->{
				System.out.println(quote.getId());
				System.out.println("***");
			});

		};
	}
}