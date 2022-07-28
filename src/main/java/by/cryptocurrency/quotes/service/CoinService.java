package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.dto.AvailableCoin;
import by.cryptocurrency.quotes.model.CoinPrice;

import java.util.List;

public interface CoinService {
    void coinsFromApi();

    CoinPrice getCurrentPrice(String symbol);

    List<AvailableCoin> getAvailableCoin();

    Object convertCoinPriceToCoinDTO(String symbol);
}