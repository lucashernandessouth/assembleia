package com.hernandes.assembleia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernandes.assembleia.domain.Associado;
import com.hernandes.assembleia.dto.AssociadoDTO;
import com.hernandes.assembleia.repositories.AssociadoRepository;
import com.hernandes.assembleia.services.exceptions.ObjectNotFoundException;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository repository;

	public Associado insert(Associado associado) {
		associado.setId(null);
		return repository.save(associado);
	}

	public Associado find(Integer id) {

		Optional<Associado> associado = repository.findById(id);
		return associado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Associado.class.getName()));
	}

	public List<Associado> findAll() {
		return repository.findAll();
	}

	public Associado fromDTO(AssociadoDTO associadoDto) {

		Associado associado = new Associado();
		associado.setNome(associadoDto.getNome());
		associado.setCpf(associadoDto.getCpf());

		return associado;
	}

}
