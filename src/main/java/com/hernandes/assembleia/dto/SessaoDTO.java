package com.hernandes.assembleia.dto;

import com.hernandes.assembleia.domain.Sessao;
import com.hernandes.assembleia.services.validation.SessaoInsert;

import java.io.Serializable;

@SessaoInsert
public class SessaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long duracao;
    private Integer idPauta;

    public SessaoDTO() {

    }

    public SessaoDTO(Sessao sessao) {
        idPauta = sessao.getPauta().getId();
        duracao = sessao.getDuracao();
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }


}
