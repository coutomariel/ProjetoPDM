package com.coutomariel.accesscontrol.model;

/**
 * Created by Mariel on 06/12/2016.
 */

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Sexo getSexo(int pos) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.ordinal() == pos) {
                return sexo;
            }
        }
        return null;
    }

}

