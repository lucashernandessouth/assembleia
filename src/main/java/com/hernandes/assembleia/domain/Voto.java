package com.hernandes.assembleia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hernandes.assembleia.enums.TipoVoto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private VotoPK id = new VotoPK();

    private Integer tipo;

    public Voto() {

    }

    public Voto(Pauta pauta, Associado associado, Integer tipo) {
        super();
        id.setPauta(pauta);
        id.setAssociado(associado);
        this.tipo = tipo;
    }

    @JsonIgnore
    public Pauta getPauta() {
        return id.getPauta();
    }

    public void setPauta(Pauta pauta) {
        id.setPauta(pauta);
    }

    public Associado getAssociado() {
        return id.getAssociado();
    }

    public void setAssociado(Associado associado) {
        id.setAssociado(associado);
    }

    public VotoPK getId() {
        return id;
    }

    public void setId(VotoPK id) {
        this.id = id;
    }

    public TipoVoto getTipo() {
        return TipoVoto.toEnum(tipo);
    }

    public void setTipo(TipoVoto tipo) {
        this.tipo = tipo.getCod();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Voto other = (Voto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
