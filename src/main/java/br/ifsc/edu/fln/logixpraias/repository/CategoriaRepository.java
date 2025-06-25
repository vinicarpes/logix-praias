package br.ifsc.edu.fln.logixpraias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsc.edu.fln.logixpraias.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}