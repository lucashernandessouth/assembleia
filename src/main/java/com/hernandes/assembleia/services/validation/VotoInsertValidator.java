package com.hernandes.assembleia.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hernandes.assembleia.domain.Associado;
import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.domain.Voto;
import com.hernandes.assembleia.dto.VotoDTO;
import com.hernandes.assembleia.exceptions.FieldMessage;
import com.hernandes.assembleia.repositories.VotoRepository;
import com.hernandes.assembleia.services.AssociadoService;
import com.hernandes.assembleia.services.PautaService;

public class VotoInsertValidator implements ConstraintValidator<VotoInsert, VotoDTO> {

	@Autowired
	private VotoRepository repository;

	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private AssociadoService associadoService;

	@Override
	public void initialize(VotoInsert ann) {
	}

	@Override
	public boolean isValid(VotoDTO votoDto, ConstraintValidatorContext context) {

		List<FieldMessage> mensagens = new ArrayList<>();

		Associado associado = associadoService.find(votoDto.getIdAssociado());
		
		if (associado == null) {
			mensagens.add(new FieldMessage("idAssociado", "Associado inexistente."));
		}

		List<Voto> votos = repository.findByPautaAndAssociado(votoDto.getIdPauta(), votoDto.getIdAssociado());
		
		if (!votos.isEmpty()) {
			mensagens.add(new FieldMessage("idAssociado", "Associado já votou nessa pauta."));
		}
		
		if (votoDto.getTipo() != 1 && votoDto.getTipo() != 2) {
			mensagens.add(new FieldMessage("tipo", "voto inválido."));
		}
		
		Pauta pauta = pautaService.find(votoDto.getIdPauta());

		if (pauta == null) {
			mensagens.add(new FieldMessage("idPauta", "Pauta inexistente."));
		} else {

			if (pauta.getSessao() == null) {
				mensagens.add(new FieldMessage("idPauta", "Pauta sem sessão de votação."));
			} else {
				long agora = System.currentTimeMillis();
				if (agora > pauta.getSessao().getFim()) {
					mensagens.add(new FieldMessage("idPauta", "Pauta fechada."));
				}
			}

		}

		for (FieldMessage e : mensagens) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return mensagens.isEmpty();
	}

}
