package com.coutomariel.accesscontrol.model;

/**
 * Created by Mariel on 05/12/2016.
 */

public enum TipoPessoa {
    FORNECEDOR("Fornecedor"),
    FUNCIONARIO("Funcionário"),
    CLIENTE("Cliente"),
    VISITANTE("Visitante");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
