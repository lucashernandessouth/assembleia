package com.hernandes.assembleia.services;

import com.hernandes.assembleia.domain.Associado;
import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.domain.Voto;
import com.hernandes.assembleia.dto.VotoDTO;
import com.hernandes.assembleia.enums.TipoVoto;
import com.hernandes.assembleia.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

	@Autowired
	private VotoRepository repository;

	public Voto insert(Voto obj) {
		return repository.save(obj);
	}

	public List<Voto> findAll() {
		return repository.findAll();
	}

	public Voto fromDTO(VotoDTO votoDto) {

		Voto voto = new Voto();

		Associado associado = new Associado();
		associado.setId(votoDto.getIdAssociado());
		voto.setAssociado(associado);

		Pauta pauta = new Pauta();
		pauta.setId(votoDto.getIdPauta());
		voto.setPauta(pauta);

		voto.setTipo(TipoVoto.toEnum(votoDto.getTipo()));
		return voto;
	}

}
