package by.cryptocurrency.quotes.config;

import by.cryptocurrency.quotes.dto.AvailableCoin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinConfig {

    @Bean
    public AvailableCoin btc() {
        return new AvailableCoin(90, "BTC");
    }

    @Bean
    public AvailableCoin eth() {
        return new AvailableCoin(80, "ETH");
    }

    @Bean
    public AvailableCoin sol() {
        return new AvailableCoin(48543, "SOL");
    }
}