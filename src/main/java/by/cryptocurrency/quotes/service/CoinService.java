package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.Coin;
import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import by.cryptocurrency.quotes.repository.CoinRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@EnableScheduling
public class CoinService {
    private CoinRepository coinRepository;
    private CoinPriceRepository coinPriceRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private List<CoinPrice> coinPriceList = new ArrayList<>();
    private List<Coin> coinList;


    public CoinService(CoinRepository coinRepository, CoinPriceRepository coinPriceRepository) {
        this.coinRepository = coinRepository;
        this.coinPriceRepository = coinPriceRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void coinsFromApi() {
        String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        coinList = Arrays
                .stream(Objects.requireNonNull(restTemplate.getForObject(url, Coin[].class)))
                .toList();
        coinPriceSaveToDb();
    }

    @Transactional
    public void coinPriceSaveToDb() {
        coinList.forEach(value -> {
            coinPriceList.add(new CoinPrice(value.getPrice_usd(), value.getSymbol()));
        });
        coinPriceRepository.saveAll(coinPriceList);
        coinPriceList.clear();
    }
}
