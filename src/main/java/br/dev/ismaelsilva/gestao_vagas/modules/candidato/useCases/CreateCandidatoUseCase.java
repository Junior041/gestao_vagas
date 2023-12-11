package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.UserAlreadyExists;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidatoUseCase {
    @Autowired
    private CandidatoRepository candidatoRepository;
    public CandidatoEntity execute(CandidatoEntity candidatoEntity){
        this.candidatoRepository.findByUsernameOrEmail(candidatoEntity.getUsername(), candidatoEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExists();
                });
        return this.candidatoRepository.save(candidatoEntity);
    }
}
