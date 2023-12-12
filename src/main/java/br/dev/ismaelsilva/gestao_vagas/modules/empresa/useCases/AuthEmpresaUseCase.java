package br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.AuthEmpresaDto;
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
import java.util.Optional;

@Service
public class AuthEmpresaUseCase {
    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaRepository empresaRepository;
    public String execute(AuthEmpresaDto authEmpresaDto) throws AuthenticationException {
        var empresa = this.empresaRepository.findByUsername(authEmpresaDto.getUsername());
        Boolean passwordMatches = this.passwordEncoder.matches(authEmpresaDto.getPassword(), empresa.get().getPassword());

        if (!passwordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create().withIssuer("javagas")
                .withSubject(empresa.get().getId().toString())
                .sign(algorithm)
        ;
        return token;

    }
}
