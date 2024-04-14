package ie.andrew.consume.controller;

import com.fasterxml.jackson.databind.JsonNode;
import ie.andrew.consume.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@RestController
public class Endpoints {

    @Autowired
    RestTemplate restTemplate;
    
    @GetMapping("/nicequote")
    String one() {
        ArrayList<Quote> quotes = new ArrayList<>();

        quotes.add(restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/2", Quote.class));

        quotes.forEach(quote->{
            System.out.println(quote.getTitle());
            System.out.println(quote.getId());
            System.out.println(quote.getUserId());
            System.out.println(quote.getBody());
            System.out.println(quote.getClass());
            System.out.println("---");
        });

        return "flippeddy floppedy do";

    }

    @GetMapping("/test")
    String two() {
        ArrayList<JsonNode> quotes = new ArrayList<>();

        quotes.add(restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/1", JsonNode.class));

        quotes.forEach(quote->{
            System.out.println(quote.get("title"));
        });

        return quotes.get(0).get("title").toString();

    }

}
