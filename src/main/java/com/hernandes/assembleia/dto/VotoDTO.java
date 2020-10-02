package com.hernandes.assembleia.dto;

import com.hernandes.assembleia.domain.Voto;
import com.hernandes.assembleia.services.validation.VotoInsert;

import java.io.Serializable;

@VotoInsert
public class VotoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPauta;
    private Integer idAssociado;
    private Integer tipo;

    public VotoDTO(Integer idPauta, Integer idAssociado, Integer tipo) {
        this.idPauta = idPauta;
        this.idAssociado = idAssociado;
        this.tipo = tipo;
    }

    public VotoDTO() {

    }

    public VotoDTO(Voto voto) {
        idPauta = voto.getPauta().getId();
        idAssociado = voto.getAssociado().getId();
        tipo = voto.getTipo().getCod();
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Integer idAssociado) {
        this.idAssociado = idAssociado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

}
