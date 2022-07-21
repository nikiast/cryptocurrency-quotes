package by.cryptocurrency.quotes;

import by.cryptocurrency.quotes.config.CoinConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CryptocurrencyQuotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptocurrencyQuotesApplication.class, args);
    }
}