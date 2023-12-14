package br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "aplicar_vaga")
public class AplicarVagaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidato_id", insertable = false, updatable = false)
    private CandidatoEntity candidatoEntity;

    @ManyToOne
    @JoinColumn(name = "candidato_id", insertable = false, updatable = false)
    private VagaEntity vagaEntity;

    @Column(name = "candidato_id")
    private UUID candidatoId;

    @Column(name = "vaga_id")
    private UUID vagaId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
