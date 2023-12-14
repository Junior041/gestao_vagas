package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;

import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.ProfileCandidatoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidatoUseCase {

    @Autowired
    private CandidatoRepository candidatoRepository;
    public ProfileCandidatoResponseDto execute(UUID idCandidato){
        CandidatoEntity candidato = this.candidatoRepository.findById(idCandidato).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado");
        });
    ProfileCandidatoResponseDto response = ProfileCandidatoResponseDto
            .builder()
            .id(candidato.getId())
            .name(candidato.getName())
            .username(candidato.getUsername())
            .description(candidato.getDescription())
            .email(candidato.getEmail())
            .build();
    return response;
    }
}
