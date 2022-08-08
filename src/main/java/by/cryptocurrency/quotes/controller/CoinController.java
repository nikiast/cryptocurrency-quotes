package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.dto.AvailableCoin;
import by.cryptocurrency.quotes.service.CoinService;
import by.cryptocurrency.quotes.service.DefaultCoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {
    private final CoinService defaultCoinService;

    public CoinController(DefaultCoinService defaultCoinService) {
        this.defaultCoinService = defaultCoinService;
    }

    @GetMapping("/available")
    public List<AvailableCoin> available() {
        return defaultCoinService.getAvailableCoin();
    }

    @GetMapping("/price/{symbol}")
    public Object getActualPrice(@PathVariable("symbol") String symbol) {
        return defaultCoinService.convertCoinPriceToCoinDTO(symbol);
    }

}