package by.cryptocurrency.quotes.config;

import by.cryptocurrency.quotes.dto.Coin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:coin.properties")
@Scope("prototype")
public class CoinConfig {

    @Bean
    public Coin coin(){
        return new Coin();
    }
}