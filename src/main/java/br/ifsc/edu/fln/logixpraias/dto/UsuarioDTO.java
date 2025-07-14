package br.ifsc.edu.fln.logixpraias.dto;

import br.ifsc.edu.fln.logixpraias.enums.EPerfil;

public record UsuarioDTO(String email, String senha, EPerfil perfil) {
}
