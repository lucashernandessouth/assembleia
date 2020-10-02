package com.hernandes.assembleia.dto;

import com.hernandes.assembleia.domain.Associado;

import java.io.Serializable;

public class AssociadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;

    public AssociadoDTO() {

    }

    public AssociadoDTO(Associado associado) {
        nome = associado.getNome();
        cpf = associado.getCpf();
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

}
