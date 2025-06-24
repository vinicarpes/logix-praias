package br.ifsc.edu.fln.logixpraias.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BombeiroMilitar extends Usuario{
    protected String mtcl;
}
