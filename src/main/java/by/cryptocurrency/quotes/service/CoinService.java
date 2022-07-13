package by.cryptocurrency.quotes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface CoinService {

    void coinsFromApiToDb();
}
