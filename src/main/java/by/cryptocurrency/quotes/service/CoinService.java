package by.cryptocurrency.quotes.service;

public interface CoinService {
    void coinsFromApi();

    Object getCurrentPrice(String symbol);
}
