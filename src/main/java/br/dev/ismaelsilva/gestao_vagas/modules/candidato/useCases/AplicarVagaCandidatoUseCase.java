package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.UserNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.exception.VagaAlreadyAplicada;
import br.dev.ismaelsilva.gestao_vagas.exception.VagaNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.AplicarVagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.AplicarVagaRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AplicarVagaCandidatoUseCase {
    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    AplicarVagaRepository aplicarVagaRepository;

    public AplicarVagaEntity execute(UUID idCandidato, UUID idVaga) {
        this.candidatoRepository.findById(idCandidato).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.vagaRepository.findById(idVaga).orElseThrow(() -> {
            throw new VagaNotFoundException();
        });

        this.aplicarVagaRepository.findAplicarVagaByVagaIdAndCandidatoId(idVaga, idCandidato).ifPresent((aplicarVagaEntity) -> {
            throw new VagaAlreadyAplicada();
        });

        AplicarVagaEntity aplicarVagaEntity = AplicarVagaEntity.builder()
                .candidatoId(idCandidato)
                .vagaId(idVaga)
                .build();

        var newVaga = this.aplicarVagaRepository.save(aplicarVagaEntity);
        return newVaga;
    }
}
