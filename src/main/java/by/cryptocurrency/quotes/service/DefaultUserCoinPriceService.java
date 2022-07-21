package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.model.UserCoinPrice;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import by.cryptocurrency.quotes.repository.UserCoinPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class DefaultUserCoinPriceService implements UserCoinLinkService {
    private final UserCoinPriceRepository userCoinPriceRepository;
    private final CoinPriceRepository coinPriceRepository;
    private final Logger log = LoggerFactory.getLogger(DefaultUserCoinPriceService.class);

    public DefaultUserCoinPriceService(UserCoinPriceRepository userCoinPriceRepository, CoinPriceRepository coinPriceRepository) {
        this.userCoinPriceRepository = userCoinPriceRepository;
        this.coinPriceRepository = coinPriceRepository;
    }

    @Transactional
    public void userCoinLinkSaveToDb(User user) {
        CoinPrice currentCoinPrice = coinPriceRepository.findLast3Coin()
                .stream()
                .filter(coinPrice -> user.getSymbol().equals(coinPrice.getSymbol()))
                .findFirst()
                .orElseThrow();
        UserCoinPrice userCoinPrice = new UserCoinPrice(user, currentCoinPrice);
        userCoinPriceRepository.save(userCoinPrice);
    }

    public void notifyOfPriceChangesMoreThanOnePercent(Map<String, CoinPrice> currentPriceMap) {
        userCoinPriceRepository.findAll().forEach(userCoinPrice -> {
            String symbol = userCoinPrice.getUserId().getSymbol();
            String username = userCoinPrice.getUserId().getUsername();

            Double oldPrice = userCoinPrice.getPrice().getPriceUsd();
            Double newPrice = currentPriceMap.get(symbol).getPriceUsd();
            Double percent = calculatingPercentage(oldPrice, newPrice);
            if (percent > 1) {
                log.warn(symbol + ", " + username + ", " + percent + "%");
            }
        });
    }

    public Double calculatingPercentage(Double oldPrice, Double newPrice) {
        return (newPrice - oldPrice) * 100 / oldPrice;
    }
}