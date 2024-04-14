package ie.andrew.consume;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

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

			JsonNode quote = restTemplate.getForObject(
					"https://jsonplaceholder.typicode.com/posts/1", JsonNode.class);
			log.info(quote.toString());

			System.out.println(quote.getNodeType());
			quote.forEach(q->{
				System.out.println(q.get("title"));
			});

			//https://jsonplaceholder.typicode.com/
		};
	}
}