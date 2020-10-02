package com.hernandes.assembleia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.dto.PautaDTO;
import com.hernandes.assembleia.repositories.PautaRepository;
import com.hernandes.assembleia.services.exceptions.ObjectNotFoundException;

@Service
public class PautaService {

	@Autowired
	private PautaRepository repository;

	public Pauta insert(Pauta pauta) {
		pauta.setId(null);
		return repository.save(pauta);
	}

	public Pauta find(Integer id) {

		Optional<Pauta> pauta = repository.findById(id);
		return pauta.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pauta.class.getName()));
	}

	public List<Pauta> findAll() {
		return repository.findAll();
	}

	public Pauta fromDTO(PautaDTO pautaDto) {

		Pauta pauta = new Pauta();
		pauta.setDescricao(pautaDto.getDescricao());

		return pauta;
	}

}
