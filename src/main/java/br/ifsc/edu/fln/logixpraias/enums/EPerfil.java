package br.ifsc.edu.fln.logixpraias.enums;

import lombok.Getter;

@Getter
public enum EPerfil {
    ADMINISTRADOR("Admin"), BOMBEIRO_MILITAR("BM"), GUARDA_VIDAS_CIVIL("GVC");

    private final String descricao;

    EPerfil(String descricao) {
        this.descricao = descricao;
    }
}
