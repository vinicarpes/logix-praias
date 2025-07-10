package br.ifsc.edu.fln.logixpraias.repository;

import br.ifsc.edu.fln.logixpraias.model.RetiradaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialWithdrawalRepository extends JpaRepository<RetiradaMaterial, Long> {
}
