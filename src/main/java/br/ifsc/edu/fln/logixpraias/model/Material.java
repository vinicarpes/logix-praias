package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @OneToOne
    private Estoque estoque = new Estoque();
    @ManyToOne
    private Categoria categoria;
}
