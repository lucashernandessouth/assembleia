package com.hernandes.assembleia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.hernandes.assembleia.enums.TipoVoto;


@Entity
public class Pauta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@OneToMany(mappedBy="id.pauta")
	private Set<Voto> votos = new HashSet<>();
	
	@OneToOne(mappedBy="pauta")
	private Sessao sessao; 
	
	public Pauta() {
		
	}

	public Pauta(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public List<String> getResultado() {
		List<String> resultado = new ArrayList<>();
	
        if(votos.isEmpty()) {
        	resultado.add("pauta ainda não foi votada");
        	return resultado;
        }
		
		int votosSim = 0;
		int votosNao = 0;
		
		for(Voto v : votos) {
			if(v.getTipo().equals(TipoVoto.SIM)) {
				votosSim++;
			}
			if(v.getTipo().equals(TipoVoto.NAO)) {
				votosNao++;
			}
		}		
		
		resultado.add("Sim: " + votosSim);
		resultado.add("Não: " + votosNao);		
		
		return resultado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Voto> getVotos() {
		return votos;
	}

	public void setVotos(Set<Voto> votos) {
		this.votos = votos;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
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
		Pauta other = (Pauta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
