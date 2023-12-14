package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.UserNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.exception.VagaNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyVagaCandidatoUseCase {
    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    VagaRepository vagaRepository;

    public void execute(UUID idCandidato, UUID idVaga) {
        this.candidatoRepository.findById(idCandidato).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.vagaRepository.findById(idVaga).orElseThrow(() -> {
            throw new VagaNotFoundException();
        });

    }
}
