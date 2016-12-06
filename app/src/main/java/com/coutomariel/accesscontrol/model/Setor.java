package com.coutomariel.accesscontrol.model;

/**
 * Created by Mariel on 05/12/2016.
 */

public enum Setor {

    ADMINISTRATIVO("Administrativo"),
    COMERCIAL("Comercial"),
    LOGISTICA("Logistica");

    private String descricao;

    Setor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Setor getSetor(int pos) {
        for (Setor s : Setor.values()) {
            if (s.ordinal() == pos) {
                return s;
            }
        }
        return null;
    }
}
