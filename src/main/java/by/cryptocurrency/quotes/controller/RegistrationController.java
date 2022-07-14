package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.service.CoinService;
import by.cryptocurrency.quotes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private UserService userService;
    private CoinService coinService;

    public RegistrationController(UserService userService, CoinService coinService) {
        this.userService = userService;
        this.coinService = coinService;
    }

    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> notify(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}