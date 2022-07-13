package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.service.DefaultCoinService;
import by.cryptocurrency.quotes.service.DefaultUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
public class RegistrationController {
    DefaultUserService defaultUserService;
    DefaultCoinService defaultCoinService;

    public RegistrationController(DefaultUserService defaultUserService, DefaultCoinService defaultCoinService) {
        this.defaultUserService = defaultUserService;
        this.defaultCoinService = defaultCoinService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        defaultUserService.save(user);
//        defaultCoinService.
        return ResponseEntity.ok(HttpStatus.OK);
    }
}