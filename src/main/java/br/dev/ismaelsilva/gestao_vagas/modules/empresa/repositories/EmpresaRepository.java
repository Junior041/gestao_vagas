package br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {
    Optional<EmpresaEntity> findEmpresaByUsernameOrEmail(String username, String empresa);
    Optional<EmpresaEntity> findByUsername(String string);
}
