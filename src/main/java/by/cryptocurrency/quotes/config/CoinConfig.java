package by.cryptocurrency.quotes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:coin.properties")
@Scope("prototype")
public class CoinConfig {
}