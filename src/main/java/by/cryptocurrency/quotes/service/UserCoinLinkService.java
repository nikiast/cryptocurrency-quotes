package by.cryptocurrency.quotes.service;

import by.cryptocurrency.quotes.model.CoinPrice;
import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.model.UserCoinLink;
import by.cryptocurrency.quotes.repository.CoinPriceRepository;
import by.cryptocurrency.quotes.repository.UserCoinLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserCoinLinkService {
    private UserCoinLinkRepository userCoinLinkRepository;
    private CoinPriceRepository coinPriceRepository;


    public UserCoinLinkService(UserCoinLinkRepository userCoinLinkRepository, CoinPriceRepository coinPriceRepository) {
        this.userCoinLinkRepository = userCoinLinkRepository;
        this.coinPriceRepository = coinPriceRepository;
    }

    @Transactional
    public void userCoinLinkSaveToDb(User user) {
        List<CoinPrice> coinPriceList = coinPriceRepository.findBySymbol(user.getSymbol());
        UserCoinLink userCoinLink = new UserCoinLink(user, coinPriceList.get(coinPriceList.size()-1));
        userCoinLinkRepository.save(userCoinLink);
    }
}