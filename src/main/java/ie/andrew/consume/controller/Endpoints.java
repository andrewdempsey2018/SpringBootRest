package ie.andrew.consume.controller;

import com.fasterxml.jackson.databind.JsonNode;
import ie.andrew.consume.model.Quote;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

        return "test 1234";

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

    @GetMapping("/getnode")
    JsonNode GetNode() {
        JsonNode quote = null;

        quote = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/5", JsonNode.class);



        return quote;

    }

    @GetMapping("/getquote")
    Quote GetQuote() {
        Quote quote = null;

        quote = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/7", Quote.class);



        return quote;

    }

    @GetMapping("/getquotenumber")
    Quote GetQuoteNumber(@RequestParam String number) {
        Quote quote = null;

        quote = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/posts/" + number, Quote.class);



        return quote;

    }

    @GetMapping("/headers")
    String Headers() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "asdf");

        ResponseEntity response = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/", HttpMethod.GET, new HttpEntity<String>(headers), JsonNode.class);

        System.out.println(response.getBody());



        return "Headers done...";

    }

}
