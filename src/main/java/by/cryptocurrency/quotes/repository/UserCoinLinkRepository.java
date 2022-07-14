package by.cryptocurrency.quotes.repository;

import by.cryptocurrency.quotes.model.UserCoinLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoinLinkRepository extends JpaRepository<UserCoinLink, Long> {
}