package br.ifsc.edu.fln.logixpraias.repository;

import org.springframework.data.domain.Page;                   // CORRETO
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import br.ifsc.edu.fln.logixpraias.model.Estoque;


@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>, JpaSpecificationExecutor<Estoque> {

    Page<Estoque> findAll(Pageable pageable);

}
