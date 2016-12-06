package com.coutomariel.accesscontrol.model;

import java.util.Date;

/**
 * Created by Mariel on 06/12/2016.
 */

public class Pessoa {

    private int id;
    private String nome;
    private Date dataAdmissao;
    private Setor setor;
    private TipoPessoa tipoPessoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", setor=" + setor +
                ", tipoPessoa=" + tipoPessoa +
                '}';
    }
}
