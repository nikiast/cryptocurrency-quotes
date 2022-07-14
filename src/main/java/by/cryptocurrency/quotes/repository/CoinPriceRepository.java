package by.cryptocurrency.quotes.repository;

import java.util.List;
import by.cryptocurrency.quotes.model.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoinPriceRepository extends JpaRepository<CoinPrice, Long> {
    List<CoinPrice> findBySymbol(String symbol);
}
