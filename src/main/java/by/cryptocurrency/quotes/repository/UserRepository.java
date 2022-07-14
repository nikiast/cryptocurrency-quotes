package by.cryptocurrency.quotes.repository;

import by.cryptocurrency.quotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}