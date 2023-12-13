package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllVagasByFilterUseCase {
    @Autowired
    private VagaRepository vagaRepository;
    public List<VagaEntity> execute(String filter){
        return this.vagaRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
