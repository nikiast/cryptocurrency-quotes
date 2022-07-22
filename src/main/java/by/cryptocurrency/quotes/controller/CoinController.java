package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.dto.CoinDTO;
import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.service.CoinService;
import by.cryptocurrency.quotes.service.DefaultCoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class CoinController {
    private final CoinService defaultCoinService;

    public CoinController(DefaultCoinService defaultCoinService) {
        this.defaultCoinService = defaultCoinService;
    }

//    @GetMapping("/available")
//    public List<CoinPrice> available() {
//        return defaultCoinService.availableCoin();
//    }

    @GetMapping("/getPrice/{symbol}")
    public Object getActualPrice(@PathVariable("symbol") String symbol) {
        return convertToCoinDTO(symbol);
    }

    private CoinDTO convertToCoinDTO(String symbol){
        CoinPrice coinPrice = defaultCoinService.getCurrentPrice(symbol);
        return new CoinDTO(coinPrice.getSymbol(), coinPrice.getPriceUsd());
    }
}