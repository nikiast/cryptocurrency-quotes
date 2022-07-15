package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final DefaultUserCoinLinkService defaultUserCoinLinkService;

    public DefaultUserService(UserRepository userRepository, DefaultUserCoinLinkService defaultUserCoinLinkService) {
        this.userRepository = userRepository;
        this.defaultUserCoinLinkService = defaultUserCoinLinkService;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
        defaultUserCoinLinkService.userCoinLinkSaveToDb(user);
    }
}