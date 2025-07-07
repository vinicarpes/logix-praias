package br.ifsc.edu.fln.logixpraias.repository;

import br.ifsc.edu.fln.logixpraias.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCategoriaId(Long categoriaId);
}
