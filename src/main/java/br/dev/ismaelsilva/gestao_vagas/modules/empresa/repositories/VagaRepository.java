package br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VagaRepository extends JpaRepository<VagaEntity, UUID> {
    //"contains - LIKE "
    List<VagaEntity> findByDescriptionContaining(String filter);
}
