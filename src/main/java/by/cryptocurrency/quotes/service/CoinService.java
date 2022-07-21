package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.Coin;

import java.util.List;

public interface CoinService {
    void coinsFromApi();

    List<Coin> availableCoin();

    Object getCurrentPrice(String symbol);
}
