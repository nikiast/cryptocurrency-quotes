package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.repository.UserCoinLinkRepository;
import by.cryptocurrency.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserCoinLinkService userCoinLinkService;

    public UserService(UserRepository userRepository, UserCoinLinkService userCoinLinkService) {
        this.userRepository = userRepository;
        this.userCoinLinkService = userCoinLinkService;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
        userCoinLinkService.userCoinLinkSaveToDb(user);
    }
}
