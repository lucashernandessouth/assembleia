package com.hernandes.assembleia.services;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hernandes.assembleia.domain.Associado;
import com.hernandes.assembleia.dto.AssociadoDTO;
import com.hernandes.assembleia.repositories.AssociadoRepository;

@ExtendWith(SpringExtension.class)
class AssociadoServiceTest {

	@InjectMocks
	AssociadoService associadoService;

	@Mock
	AssociadoRepository associadoRepository;

	Associado associado = new Associado(1, "Lucas", "02506089012");
	AssociadoDTO associadoDTO = new AssociadoDTO(associado);

	@Test
	void insert() {
		BDDMockito.when(associadoRepository.save(associado)).thenReturn(associado);
		associadoService.insert(associado);
		Assertions.assertEquals(associado, associadoService.insert(associado));
	}

	@Test
	void findAll() {
		List<Associado> associados = Collections.singletonList(associado);
		BDDMockito.when(associadoRepository.findAll()).thenReturn(associados);
		associadoService.findAll();
		Assertions.assertEquals(associados, associadoService.findAll());
	}

	@Test
	void fromDTO() {
		associadoService.fromDTO(associadoDTO);
		Assertions.assertNotNull(associadoDTO);
	}
}
