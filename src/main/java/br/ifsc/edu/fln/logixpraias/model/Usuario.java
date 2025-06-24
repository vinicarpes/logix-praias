package br.ifsc.edu.fln.logixpraias.model;

import br.ifsc.edu.fln.logixpraias.enums.EGraduacao;
import br.ifsc.edu.fln.logixpraias.enums.ESetor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public abstract class Usuario {
    @Id
    protected Long id;
    protected String nome;
    protected String email;
    protected LocalDate dataCadastro = LocalDate.now();
    protected EGraduacao graduacao;
    protected ESetor setor;

}
