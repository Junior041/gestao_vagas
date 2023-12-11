package br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVagaUseCase {

    @Autowired
    private VagaRepository vagaRepository;

    public VagaEntity execute(VagaEntity vagaEntity){
        return this.vagaRepository.save(vagaEntity);
    }

}
