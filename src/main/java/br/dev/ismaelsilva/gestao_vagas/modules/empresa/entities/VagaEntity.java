package br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "vaga")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String benefits;

    @NotBlank(message = "Esse campo é obrigatório")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    private EmpresaEntity empresaEntity;

    @Column(name = "empresa_id", nullable = false)
    private UUID empresaId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
