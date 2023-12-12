package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.AuthCandidatoRequestDto;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.AuthCandidatoResponseDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidatoUseCase {
    @Value("${security.token.secret.candidate}")
    private String secretKey;
    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthCandidatoResponseDto execute(AuthCandidatoRequestDto authCandidatoRequestDto) throws AuthenticationException {
        var candidato = this.candidatoRepository.findByUsername(authCandidatoRequestDto.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorretos.");
                });
        Boolean passwordMatches = this.passwordEncoder.matches(authCandidatoRequestDto.password(), candidato.getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(candidato.getId().toString())
                .withClaim("roles", Arrays.asList("candidato"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        var authAuthenticateResponse = AuthCandidatoResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authAuthenticateResponse;
    }
}
