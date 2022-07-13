package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.repository.CoinRepository;
import by.cryptocurrency.quotes.service.CoinService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinController {
    CoinService defaultCoinService;
    CoinRepository coinRepository;

    public CoinController(CoinService defaultCoinService, CoinRepository coinRepository) {
        this.defaultCoinService = defaultCoinService;
        this.coinRepository = coinRepository;
    }
}