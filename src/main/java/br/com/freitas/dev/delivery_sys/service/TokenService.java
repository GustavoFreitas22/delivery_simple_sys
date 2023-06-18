package br.com.freitas.dev.delivery_sys.service;

import br.com.freitas.dev.delivery_sys.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String getSubject(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return JWT.require(Algorithm.HMAC256("delivery")).withIssuer("Client").build().verify(decoded).getSubject();
    }

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("Client")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(5)
                        .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("delivery"));
    }
}
