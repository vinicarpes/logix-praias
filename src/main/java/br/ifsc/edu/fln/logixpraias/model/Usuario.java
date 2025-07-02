package br.ifsc.edu.fln.logixpraias.model;

import br.ifsc.edu.fln.logixpraias.enums.EGraduacao;
import br.ifsc.edu.fln.logixpraias.enums.ESetor;
import br.ifsc.edu.fln.logixpraias.enums.EPerfil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    protected Long id;
    protected String nome;
    protected String email;
    protected String telefone;
    protected String mtcl;
    @Enumerated(EnumType.STRING)
    protected EPerfil perfil;
    protected LocalDate dataCadastro = LocalDate.now();
    @Enumerated(EnumType.STRING)
    protected EGraduacao graduacao;
    @Enumerated(EnumType.STRING)
    protected ESetor setor;

}
