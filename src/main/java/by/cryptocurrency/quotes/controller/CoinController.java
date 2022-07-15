package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.model.Coin;
import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.repository.CoinRepository;
import by.cryptocurrency.quotes.service.DefaultCoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {
    DefaultCoinService defaultCoinService;
    CoinRepository coinRepository;

    public CoinController(DefaultCoinService defaultCoinService, CoinRepository coinRepository) {
        this.defaultCoinService = defaultCoinService;
        this.coinRepository = coinRepository;
    }

    @GetMapping("/available")
    public List<Coin> available() {
        return coinRepository.findAll();
    }

    @GetMapping("/getPrice/{symbol}")
    public CoinPrice getActualPrice(@PathVariable("symbol") String symbol) {
        defaultCoinService.getCurrentPrice(symbol);
        return defaultCoinService.getCurrentPrice(symbol);
    }
}