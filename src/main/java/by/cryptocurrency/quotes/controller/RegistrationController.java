package by.cryptocurrency.quotes.controller;

import by.cryptocurrency.quotes.model.User;
import by.cryptocurrency.quotes.service.DefaultUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private DefaultUserService defaultUserService;

    public RegistrationController(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    @PostMapping("/notify")
    public ResponseEntity<HttpStatus> registration(@RequestBody User user) {
        defaultUserService.save(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}