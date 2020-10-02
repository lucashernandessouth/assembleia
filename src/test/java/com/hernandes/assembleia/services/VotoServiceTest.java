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
import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.domain.Voto;
import com.hernandes.assembleia.dto.VotoDTO;
import com.hernandes.assembleia.repositories.VotoRepository;

@ExtendWith(SpringExtension.class)
class VotoServiceTest {
    @InjectMocks
    VotoService votoService;

    @Mock
    VotoRepository votoRepository;

    Pauta pauta = new Pauta(1, "Devemos testar?");
    Associado associado = new Associado(1, "Lucas", "02506089012");
    Voto voto = new Voto(pauta, associado, 1);
    VotoDTO votoDTO = new VotoDTO(1, 1, 1);	

    @Test
    void insert() {
        BDDMockito.when(votoRepository.save(voto)).thenReturn(voto);
        votoService.insert(voto);
        Assertions.assertEquals(voto, votoService.insert(voto));
    }

    @Test
    void findAll() {
        List<Voto> votos = Collections.singletonList(voto);
        BDDMockito.when(votoRepository.findAll()).thenReturn(votos);
        votoService.findAll();
        Assertions.assertEquals(votos, votoService.findAll());
    }

    @Test
    void fromDTO() {
        votoService.fromDTO(votoDTO);
        Assertions.assertNotNull(votoDTO);
    }
}