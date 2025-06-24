package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RetiradaMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private String observacao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Material material;

}
