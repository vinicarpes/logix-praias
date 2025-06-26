package br.ifsc.edu.fln.logixpraias.repository;

import br.ifsc.edu.fln.logixpraias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
