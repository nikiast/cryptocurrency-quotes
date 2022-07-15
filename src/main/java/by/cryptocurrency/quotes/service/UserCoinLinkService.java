package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.model.User;

import java.util.Map;

public interface UserCoinLinkService {
    void userCoinLinkSaveToDb(User user);

    void notifyOfPriceChangesMoreThanOnePercent(Map<String, CoinPrice> currentPriceMap);

    Double calculatingPercentage(Double oldPrice, Double newPrice);
}
