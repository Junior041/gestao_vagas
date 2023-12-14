package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.UserAlreadyExists;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidatoUseCase {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CandidatoRepository candidatoRepository;
    public CandidatoEntity execute(CandidatoEntity candidatoEntity){
        this.candidatoRepository.findByUsernameOrEmail(candidatoEntity.getUsername(), candidatoEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExists();
                });
        candidatoEntity.setPassword(this.passwordEncoder.encode(candidatoEntity.getPassword()));
        return this.candidatoRepository.save(candidatoEntity);
    }
}
