package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.Coin;
import by.cryptocurrency.quotes.repository.CoinRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@EnableScheduling
public class DefaultCoinService {
    CoinRepository coinRepository;
    RestTemplate restTemplate = new RestTemplate();
    List<Coin> coinList;

    public DefaultCoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void coinsFromApiToDb() {
        String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        coinList = Arrays
                .stream(Objects.requireNonNull(restTemplate.getForObject(url, Coin[].class)))
                .toList();
        coinRepository.saveAll(coinList);
    }

    public void costChangeCoins() {

    }
}
