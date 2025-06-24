package br.ifsc.edu.fln.logixpraias.enums;

public enum ESetor {
    BBM_1("1° BBM"),
    BBM_1_2("1°/2° BBM"),
    BBM_1_2_1("1°/2°/1° BBM"),
    BBM_1_2_2("1°/2°/2° BBM"),
    BBM_1_3_1("1°/3°/1° BBM"),
    BBM_1_3("1°/3°BBM"),
    CEBM("CEBM");

    private final String descricao;

    ESetor(String descricao) {
        this.descricao = descricao;
    }
}
