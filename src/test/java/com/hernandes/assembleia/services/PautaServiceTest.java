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

import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.dto.PautaDTO;
import com.hernandes.assembleia.repositories.PautaRepository;

@ExtendWith(SpringExtension.class)
class PautaServiceTest {
	
    @InjectMocks
    PautaService pautaService;

    @Mock
    PautaRepository pautaRepository;
    
    Pauta pauta = new Pauta(1, "Devemos testar?");
    PautaDTO pautaDto = new PautaDTO(pauta);
    
    @Test
    void insert() {
        BDDMockito.when(pautaRepository.save(pauta)).thenReturn(pauta);
        pautaService.insert(pauta);
        Assertions.assertEquals(pauta, pautaService.insert(pauta));
    }
    
    @Test
    void findAll() {
        List<Pauta> pautas = Collections.singletonList(pauta);
        BDDMockito.when(pautaRepository.findAll()).thenReturn(pautas);
        pautaService.findAll();
        Assertions.assertEquals(pautas, pautaService.findAll());
    }
	
    @Test
    void fromDTO() {
    	pautaService.fromDTO(pautaDto);
        Assertions.assertNotNull(pautaDto);
    }    
}
