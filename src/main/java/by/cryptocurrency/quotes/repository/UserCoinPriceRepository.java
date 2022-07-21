package by.cryptocurrency.quotes.repository;

import by.cryptocurrency.quotes.model.UserCoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoinPriceRepository extends JpaRepository<UserCoinPrice, Long> {
}