package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.model.UserCoinLink;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import by.cryptocurrency.quotes.repository.UserCoinLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class DefaultUserCoinLinkService implements UserCoinLinkService {
    private final UserCoinLinkRepository userCoinLinkRepository;
    private final CoinPriceRepository coinPriceRepository;
    private final Logger log = LoggerFactory.getLogger(DefaultUserCoinLinkService.class);

    public DefaultUserCoinLinkService(UserCoinLinkRepository userCoinLinkRepository, CoinPriceRepository coinPriceRepository) {
        this.userCoinLinkRepository = userCoinLinkRepository;
        this.coinPriceRepository = coinPriceRepository;
    }

    @Transactional
    public void userCoinLinkSaveToDb(User user) {
        CoinPrice currentCoinPrice = coinPriceRepository.findLast3Coin()
                .stream()
                .filter(coinPrice -> user.getSymbol().equals(coinPrice.getSymbol()))
                .findFirst()
                .get();
        UserCoinLink userCoinLink = new UserCoinLink(user, currentCoinPrice);
        userCoinLinkRepository.save(userCoinLink);
    }

    public void notifyOfPriceChangesMoreThanOnePercent(Map<String, CoinPrice> currentPriceMap) {
        userCoinLinkRepository.findAll().forEach(userCoinLink -> {
            String symbol = userCoinLink.getUserId().getSymbol();
            String username = userCoinLink.getUserId().getUsername();

            Double oldPrice = userCoinLink.getPrice().getPrice();
            Double newPrice = currentPriceMap.get(symbol).getPrice();
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