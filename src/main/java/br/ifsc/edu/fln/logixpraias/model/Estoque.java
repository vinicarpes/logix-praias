package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade = 0;
    @OneToOne
    @JoinColumn(name = "material_id")
    private Material material;
}
