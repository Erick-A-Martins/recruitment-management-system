package br.com.erickmartins.gestao_vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String generateToken(UUID id, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create()
                .withIssuer("javagas")
                .withSubject(id.toString())
                .withClaim("roles", List.of("COMPANY"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
    }
}
