package br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.AuthEmpresaDto;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.AuthEmpresaResponseDto;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.EmpresaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
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
import java.util.Optional;

@Service
public class AuthEmpresaUseCase {
    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaRepository empresaRepository;
    public AuthEmpresaResponseDto execute(AuthEmpresaDto authEmpresaDto) throws AuthenticationException {
        var empresa = this.empresaRepository.findByUsername(authEmpresaDto.getUsername());
        Boolean passwordMatches = this.passwordEncoder.matches(authEmpresaDto.getPassword(), empresa.get().getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        String token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(empresa.get().getId().toString())
                .withClaim("roles", Arrays.asList("EMPRESA"))
                .sign(algorithm)
        ;

        var response = AuthEmpresaResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return response;
    }
}
