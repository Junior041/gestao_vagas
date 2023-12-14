package br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases;

import br.dev.ismaelsilva.gestao_vagas.exception.EmpresaNaoEncontrada;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVagaUseCase {

    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public VagaEntity execute(VagaEntity vagaEntity){
        this.empresaRepository.findById(vagaEntity.getEmpresaId()).orElseThrow(() -> {
            throw new EmpresaNaoEncontrada();
        });
        return this.vagaRepository.save(vagaEntity);
    }

}
