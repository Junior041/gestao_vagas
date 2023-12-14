package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import br.dev.ismaelsilva.gestao_vagas.exception.UserNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.exception.VagaNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.AplicarVagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.AplicarVagaRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.repoitories.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class AplicarVagaCandidatoUseCaseTest {
    @Mock
    CandidatoRepository candidatoRepository;

    @Mock
    VagaRepository vagaRepository;

    @Mock
    AplicarVagaRepository aplicarVagaRepository;

    @InjectMocks
    private AplicarVagaCandidatoUseCase applyVagaCandidatoUseCase;
    @Test
    @DisplayName("Não deve ser possivel aplica a vaga se o candidato não existir")
    public void nao_deve_ser_possivel_aplicar_a_vaga_se_o_candidato_nao_existir (){
        try{
            this.applyVagaCandidatoUseCase.execute(null,null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }
    @Test
    @DisplayName("Não deve ser possivel aplicar a vaga se a vaga não existir")
    public void nao_deve_ser_possivel_aplicar_a_vaga_se_a_vaga_nao_existir(){
        UUID idCandidato = UUID.randomUUID();
        CandidatoEntity candidato = new CandidatoEntity();
        candidato.setId(idCandidato);
        when(candidatoRepository.findById(idCandidato)).thenReturn(Optional.of(candidato));

        try{
            this.applyVagaCandidatoUseCase.execute(idCandidato,null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(VagaNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Deve ser possivel aplicar a vaga")
    public void deve_ser_possivel_aplicar_a_vaga(){
        UUID idCandidato = UUID.randomUUID();
        UUID idVaga = UUID.randomUUID();
        when(candidatoRepository.findById(idCandidato)).thenReturn(Optional.of(new CandidatoEntity()));
        when(vagaRepository.findById(idVaga)).thenReturn(Optional.of(new VagaEntity()));

        AplicarVagaEntity aplicarVagaEntity = AplicarVagaEntity.builder()
                .id(UUID.randomUUID())
                .candidatoId(idCandidato)
                .vagaId(idVaga)
                .build();
        when(aplicarVagaRepository.save(any(AplicarVagaEntity.class))).thenReturn(aplicarVagaEntity);
        var result = this.applyVagaCandidatoUseCase.execute(idCandidato, idVaga);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}

