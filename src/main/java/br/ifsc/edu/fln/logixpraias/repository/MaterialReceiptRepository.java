package br.ifsc.edu.fln.logixpraias.repository;

import br.ifsc.edu.fln.logixpraias.model.RecebimentoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialReceiptRepository extends JpaRepository<RecebimentoMaterial, Long> {
}
