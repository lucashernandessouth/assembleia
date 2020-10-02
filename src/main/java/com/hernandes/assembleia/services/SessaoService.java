package com.hernandes.assembleia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.domain.Sessao;
import com.hernandes.assembleia.dto.SessaoDTO;
import com.hernandes.assembleia.repositories.SessaoRepository;
import com.hernandes.assembleia.services.exceptions.ObjectNotFoundException;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository repository;

	public Sessao insert(Sessao sessao) {

		sessao.setId(null);
		sessao.setInicio(System.currentTimeMillis());
		sessao.setDuracao((sessao.getDuracao() == 0) ? 60000 : sessao.getDuracao());
		sessao.setFim(sessao.getInicio() + sessao.getDuracao());
		return repository.save(sessao);
	}

	public Sessao find(Integer id) {

		Optional<Sessao> sessao = repository.findById(id);
		return sessao.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Sessao.class.getName()));
	}

	public List<Sessao> findAll() {
		return repository.findAll();
	}

	public Sessao fromDTO(SessaoDTO sessaoDto) {

		Sessao sessao = new Sessao();

		Pauta pauta = new Pauta();
		pauta.setId(sessaoDto.getIdPauta());

		sessao.setPauta(pauta);
		sessao.setDuracao(sessaoDto.getDuracao());

		return sessao;
	}

}
