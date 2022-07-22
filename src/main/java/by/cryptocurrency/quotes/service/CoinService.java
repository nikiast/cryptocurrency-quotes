package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.CoinPrice;

public interface CoinService {
    void coinsFromApi();

    CoinPrice getCurrentPrice(String symbol);
}
