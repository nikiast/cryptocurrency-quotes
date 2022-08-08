package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.dto.AvailableCoin;
import by.cryptocurrency.quotes.model.Coin;

import java.util.List;

public interface CoinService {
    void coinsFromApi();

    Coin getCurrentPrice(String symbol);

    List<AvailableCoin> getAvailableCoin();

    Object convertCoinPriceToCoinDTO(String symbol);
}