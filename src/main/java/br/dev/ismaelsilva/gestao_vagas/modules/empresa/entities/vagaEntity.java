package br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "vaga")
@Data
public class vagaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    private String level;

    @ManyToOne
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    private EmpresaEntity empresaEntity;

    @Column(name = "empresa_id")
    private UUID empresaId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
