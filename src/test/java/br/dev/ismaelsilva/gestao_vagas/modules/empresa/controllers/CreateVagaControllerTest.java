package br.dev.ismaelsilva.gestao_vagas.modules.empresa.controllers;

import br.dev.ismaelsilva.gestao_vagas.exception.EmpresaNaoEncontrada;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.CreateVagaDto;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.EmpresaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.utils.TestUtils;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateVagaControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private EmpresaRepository empresaRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @Description("Deve verificar se é possivel criar uma nova vaga")
    public void deve_ser_possivel_criar_uma_nova_vaga() throws Exception {

        EmpresaEntity empresaEntity = EmpresaEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("email@company.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME").build();

        var empresa = this.empresaRepository.saveAndFlush(empresaEntity);

        CreateVagaDto vagaDto = CreateVagaDto.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/empresa/vaga/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(vagaDto))
                        .header("Authorization", TestUtils.generateToken(empresa.getId()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @Description("Não deve ser possível criar uma vaga se a empresa não for valida")
    public void nao_deve_ser_possivel_criar_uma_vaga_se_a_empresa_nao_for_valida() throws Exception {
        CreateVagaDto vagaDto = CreateVagaDto.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        try {
            mvc.perform(MockMvcRequestBuilders.post("/empresa/vaga/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtils.objectToJSON(vagaDto))
                    .header("Authorization", TestUtils.generateToken(UUID.randomUUID()))
            );
        } catch (Exception e) {
            assertThat(e).isInstanceOf(EmpresaNaoEncontrada.class);
        }

    }


}
