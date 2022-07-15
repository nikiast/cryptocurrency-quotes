package by.cryptocurrency.quotes.repository;

import by.cryptocurrency.quotes.model.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CoinPriceRepository extends JpaRepository<CoinPrice, Long> {
    @Query(value = "SELECT * FROM coin_price ORDER BY ID DESC LIMIT 3", nativeQuery = true)
    List<CoinPrice> findLast3Coin();
}
