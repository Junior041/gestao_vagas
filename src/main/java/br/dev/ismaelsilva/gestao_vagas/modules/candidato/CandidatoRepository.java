package br.dev.ismaelsilva.gestao_vagas.modules.candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, UUID> {
    Optional<CandidatoEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidatoEntity> findByUsername(String username);
}
