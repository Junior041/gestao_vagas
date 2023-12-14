package br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories;

import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.AplicarVagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AplicarVagaRepository extends JpaRepository<AplicarVagaEntity, UUID> {
    Optional<AplicarVagaEntity> findAplicarVagaByVagaIdAndCandidatoId(UUID vagaId, UUID candidatoId);
}
