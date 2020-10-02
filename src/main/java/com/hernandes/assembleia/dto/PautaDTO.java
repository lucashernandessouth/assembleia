package com.hernandes.assembleia.dto;

import com.hernandes.assembleia.domain.Pauta;

import java.io.Serializable;

public class PautaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;

    public PautaDTO() {

    }

    public PautaDTO(Pauta pauta) {
        descricao = pauta.getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
