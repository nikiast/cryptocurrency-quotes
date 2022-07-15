package by.cryptocurrency.quotes.repository;

import by.cryptocurrency.quotes.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Integer> {
}
