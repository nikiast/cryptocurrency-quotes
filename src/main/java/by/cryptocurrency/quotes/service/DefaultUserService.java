package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final DefaultUserCoinPriceService defaultUserCoinPriceService;

    public DefaultUserService(UserRepository userRepository, DefaultUserCoinPriceService defaultUserCoinPriceService) {
        this.userRepository = userRepository;
        this.defaultUserCoinPriceService = defaultUserCoinPriceService;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
        defaultUserCoinPriceService.userCoinLinkSaveToDb(user);
    }
}