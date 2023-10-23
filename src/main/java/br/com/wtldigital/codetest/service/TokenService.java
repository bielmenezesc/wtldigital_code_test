package br.com.wtldigital.codetest.service;

import br.com.wtldigital.codetest.model.Pessoa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.time.Instant;

@Service
public class TokenService {

    Instant expirationTime = LocalDateTime.now()
    .plusMinutes(10)
    .toInstant(ZoneOffset.of("-03:00"));

    
    public String gerarToken(Pessoa usuario) {
        return JWT.create()
                .withIssuer("automoveis")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(Date.from(expirationTime)
                ).sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("automoveis")
                .build().verify(token).getSubject();

    }
}
