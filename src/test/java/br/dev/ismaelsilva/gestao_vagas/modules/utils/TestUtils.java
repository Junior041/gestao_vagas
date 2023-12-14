package br.dev.ismaelsilva.gestao_vagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {
    public static String objectToJSON(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idEmpresa){
        Algorithm algorithm = Algorithm.HMAC256("JAVAGAS_@123#");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        return JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(idEmpresa.toString())
                .withClaim("roles", Arrays.asList("EMPRESA"))
                .sign(algorithm)
                ;
    }

}
