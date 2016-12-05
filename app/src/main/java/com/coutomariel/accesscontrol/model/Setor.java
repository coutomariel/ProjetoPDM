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
}
