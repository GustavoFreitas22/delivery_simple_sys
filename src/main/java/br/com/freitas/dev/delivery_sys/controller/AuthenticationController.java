package br.com.freitas.dev.delivery_sys.controller;

import br.com.freitas.dev.delivery_sys.dto.LoginDto;
import br.com.freitas.dev.delivery_sys.model.User;
import br.com.freitas.dev.delivery_sys.repository.impl.UserRepositoryImpl;
import br.com.freitas.dev.delivery_sys.service.AuthenticateService;
import br.com.freitas.dev.delivery_sys.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositoryImpl repository;

    @Autowired
    private AuthenticateService service;

    @PostMapping("/register")
    public ResponseEntity<String> createNewUser(@RequestBody User user) {
        try {

            User userExist = repository.getUserByLogin(user.getLogin());

            if (userExist != null) {
                return new ResponseEntity<>("Error to create! User aready exist!", HttpStatus.BAD_REQUEST);
            }

            Boolean created = repository.createNewUser(new User(user.getLogin(), service.criptPassword(user.getPassword())));
            if (!created) {
                return new ResponseEntity<>("Error to create a new user!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User created with success!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authenticate.getPrincipal();

        return tokenService.generateToken(user);
    }

}
