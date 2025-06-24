package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RecebimentoMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private String descricao;
    private LocalDateTime dataRecebimento = LocalDateTime.now();
    private LocalDate dataValidade = null;
    private String fornecedor;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Material material;
}
