package com.hernandes.assembleia.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.domain.Sessao;
import com.hernandes.assembleia.dto.SessaoDTO;
import com.hernandes.assembleia.exceptions.FieldMessage;
import com.hernandes.assembleia.repositories.SessaoRepository;
import com.hernandes.assembleia.services.PautaService;

public class SessaoInsertValidator implements ConstraintValidator<SessaoInsert, SessaoDTO> {

	@Autowired
	private SessaoRepository repository;
	
	@Autowired
	private PautaService pautaService;
	
	@Override
	public void initialize(SessaoInsert ann) {
	}

	@Override
	public boolean isValid(SessaoDTO sessaoDto, ConstraintValidatorContext context) {

		List<FieldMessage> mensagens = new ArrayList<>();		
		
		Pauta pauta = pautaService.find(sessaoDto.getIdPauta());

		if (pauta == null) {
			mensagens.add(new FieldMessage("idPauta", "Pauta inexistente."));
		}
		
		List<Sessao> sessoes = repository.findByPauta(sessaoDto.getIdPauta());

		if (!sessoes.isEmpty()) {
			mensagens.add(new FieldMessage("idPauta", "Pauta já possui sessão de votação."));
		}

		for (FieldMessage e : mensagens) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return mensagens.isEmpty();
	}
	
}
