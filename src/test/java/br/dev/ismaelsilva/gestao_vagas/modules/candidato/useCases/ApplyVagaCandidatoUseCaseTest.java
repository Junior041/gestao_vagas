package br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases;
import static org.mockito.Mockito.when;
import br.dev.ismaelsilva.gestao_vagas.exception.UserNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.exception.VagaNotFoundException;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
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
public class ApplyVagaCandidatoUseCaseTest {
    @Mock
    CandidatoRepository candidatoRepository;

    @Mock
    VagaRepository vagaRepository;

    @InjectMocks
    private ApplyVagaCandidatoUseCase applyVagaCandidatoUseCase;
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


}

