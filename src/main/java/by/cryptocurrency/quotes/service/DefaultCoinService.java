package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.Coin;
import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import by.cryptocurrency.quotes.repository.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class DefaultCoinService implements CoinService {
    private final CoinPriceRepository coinPriceRepository;
    private final DefaultUserCoinLinkService defaultUserCoinLinkService;
    private final CoinRepository coinRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
    private List<CoinPrice> coinPriceList = new ArrayList<>();
    private final Logger log = LoggerFactory.getLogger(DefaultUserCoinLinkService.class);
    private Boolean flag = false;


    public DefaultCoinService(CoinPriceRepository coinPriceRepository,
                              DefaultUserCoinLinkService defaultUserCoinLinkService,
                              CoinRepository coinRepository) {
        this.coinPriceRepository = coinPriceRepository;
        this.defaultUserCoinLinkService = defaultUserCoinLinkService;
        this.coinRepository = coinRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void coinsFromApi() {
        try {
            Coin[] coins = Objects.requireNonNull(restTemplate.getForObject(url, Coin[].class));
            Arrays.stream(coins).forEach(coin -> {
                coinPriceList.add(new CoinPrice(coin.getPrice_usd(), coin.getSymbol()));
            });
            if (!flag) {
                saveCoinToDb(coins);
            }
            coinPriceRepository.saveAll(coinPriceList);
            defaultUserCoinLinkService.notifyOfPriceChangesMoreThanOnePercent(coinPriceListToMap(coinPriceList));
            coinPriceList.clear();
        } catch (HttpServerErrorException e) {
            log.info("Incorrect response from the server" + e);
        }
    }

    public Map<String, CoinPrice> coinPriceListToMap(List<CoinPrice> coinPriceList) {
        return coinPriceList
                .stream()
                .collect(Collectors.toMap(CoinPrice::getSymbol, k -> k));
    }

    public CoinPrice getCurrentPrice(String symbol) {
        return coinPriceListToMap(coinPriceRepository.findLast3Coin()).get(symbol);
    }

    public void saveCoinToDb(Coin[] coins) {
        if (coinRepository.findAll().isEmpty() || !(coinRepository.findAll().equals(Arrays.stream(coins).toList()))) {
            coinRepository.saveAll(Arrays.stream(coins).toList());
        }
        flag = true;
    }
}
