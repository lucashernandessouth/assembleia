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
import com.hernandes.assembleia.domain.Sessao;
import com.hernandes.assembleia.dto.SessaoDTO;
import com.hernandes.assembleia.repositories.SessaoRepository;

@ExtendWith(SpringExtension.class)
class SessaoServiceTest {

    @InjectMocks
    SessaoService sessaoService;

    @Mock
    SessaoRepository sessaoRepository;
    
    Pauta pauta = new Pauta(1, "Devemos testar?");
    long agora = System.currentTimeMillis();
    long dezMinutos = 600000;
    Sessao sessao = new Sessao(1, agora, dezMinutos, agora + dezMinutos, pauta);
    SessaoDTO sessaoDto = new SessaoDTO(sessao); 
    
    
    @Test
    void insert() {
        BDDMockito.when(sessaoRepository.save(sessao)).thenReturn(sessao);
        sessaoService.insert(sessao);
        Assertions.assertEquals(sessao, sessaoService.insert(sessao));
    }
    
    @Test
    void findAll() {
        List<Sessao> sessoes = Collections.singletonList(sessao);
        BDDMockito.when(sessaoRepository.findAll()).thenReturn(sessoes);
        sessaoService.findAll();
        Assertions.assertEquals(sessoes, sessaoService.findAll());
    }
	
    @Test
    void fromDTO() {
    	sessaoService.fromDTO(sessaoDto);
        Assertions.assertNotNull(sessaoDto);
    }		
}
