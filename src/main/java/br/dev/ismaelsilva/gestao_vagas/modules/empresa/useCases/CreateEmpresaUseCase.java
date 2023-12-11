package br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.EmpresaAlreadyExists;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.EmpresaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEmpresaUseCase {
    @Autowired
    private EmpresaRepository empresaRepository;
    public EmpresaEntity execute(EmpresaEntity empresaEntity){
        this.empresaRepository.findEmpresaByUsernameOrEmail(empresaEntity.getUsername(), empresaEntity.getEmail())
                .ifPresent((empresa) -> {
                    throw new EmpresaAlreadyExists();
                });
        return this.empresaRepository.save(empresaEntity);
    }
}
