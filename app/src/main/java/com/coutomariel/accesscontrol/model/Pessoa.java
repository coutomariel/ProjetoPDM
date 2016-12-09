package com.coutomariel.accesscontrol.model;

import java.util.Date;

/**
 * Created by Mariel on 06/12/2016.
 */

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private Date dataAdmissao;
    private Setor setor;
    private Sexo sexo;
    private TipoPessoa tipoPessoa;
    private String caminhoFoto;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCaminhoFoto() {return caminhoFoto;}

    public void setCaminhoFoto(String caminhoFoto) {this.caminhoFoto = caminhoFoto;}

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", setor=" + setor +
                ", sexo=" + sexo +
                ", tipoPessoa=" + tipoPessoa +
                '}';
    }
}
