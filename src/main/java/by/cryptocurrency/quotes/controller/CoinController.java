package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.model.Coin;
import by.cryptocurrency.quotes.service.CoinService;
import by.cryptocurrency.quotes.service.DefaultCoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coin")
public class CoinController {
    private final CoinService defaultCoinService;

    public CoinController(DefaultCoinService defaultCoinService) {
        this.defaultCoinService = defaultCoinService;
    }

    @GetMapping("/available")
    public List<Coin> available() {
        return defaultCoinService.availableCoin();
    }

    @GetMapping("/getPrice/{symbol}")
    public Object getActualPrice(@PathVariable("symbol") String symbol) {
        Optional<Object> findCoinPrice = Optional.ofNullable(defaultCoinService.getCurrentPrice(symbol));
        return findCoinPrice.orElseThrow();
    }
}