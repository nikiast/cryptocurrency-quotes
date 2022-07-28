package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.config.CoinConfig;
import by.cryptocurrency.quotes.dto.AvailableCoin;
import by.cryptocurrency.quotes.dto.CoinDTO;
import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    private final DefaultUserCoinPriceService defaultUserCoinPriceService;

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoinConfig.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
    private final List<CoinPrice> coinPriceList = new ArrayList<>();
    private final Logger log = LoggerFactory.getLogger(DefaultUserCoinPriceService.class);


    public DefaultCoinService(CoinPriceRepository coinPriceRepository,
                              DefaultUserCoinPriceService defaultUserCoinPriceService) {
        this.coinPriceRepository = coinPriceRepository;
        this.defaultUserCoinPriceService = defaultUserCoinPriceService;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void coinsFromApi() {
        try {
            CoinPrice[] coins = Objects.requireNonNull(restTemplate.getForObject(url, CoinPrice[].class));
            Arrays.stream(coins).forEach(coin -> {
                coinPriceList.add(new CoinPrice(coin.getPriceUsd(), coin.getSymbol()));
            });

            coinPriceRepository.saveAll(coinPriceList);
            defaultUserCoinPriceService.notifyOfPriceChangesMoreThanOnePercent(coinPriceListToMap(coinPriceList));
            coinPriceList.clear();
        } catch (HttpServerErrorException e) {
            log.error("Incorrect response from the server" + e);
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

    public List<AvailableCoin> getAvailableCoin() {
        return List.of(context.getBean("btc", AvailableCoin.class),
                context.getBean("eth", AvailableCoin.class),
                context.getBean("sol", AvailableCoin.class));
    }

    public CoinDTO convertCoinPriceToCoinDTO(String symbol) {
        CoinPrice coinPrice = getCurrentPrice(symbol);
        return new CoinDTO(coinPrice.getSymbol(), coinPrice.getPriceUsd());
    }
}