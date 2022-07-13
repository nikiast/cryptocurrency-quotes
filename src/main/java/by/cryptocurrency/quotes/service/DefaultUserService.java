package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService {
    UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }
}
