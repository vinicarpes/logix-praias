package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gvc extends Usuario{
    protected String cpf;
}
