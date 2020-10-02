package com.hernandes.assembleia.resources;

import com.hernandes.assembleia.domain.Sessao;
import com.hernandes.assembleia.dto.SessaoDTO;
import com.hernandes.assembleia.services.SessaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sessoes")
public class SessaoResource {

    @Autowired
    private SessaoService service;

    @ApiOperation(value = "inclusão de sessão de votação")
    @ApiResponses({
        @ApiResponse(code = 201, message = "sessão de votação criada com sucesso"),
        @ApiResponse(code = 400, message = "pauta já possui sessão de votação"),
        @ApiResponse(code = 404, message = "sessão de votação deve ser criada com uma pauta existente.")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody SessaoDTO sessaoDto) {

        Sessao sessao = service.fromDTO(sessaoDto);
        sessao = service.insert(sessao);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(sessao.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "busca uma sessão de votação")
    @ApiResponses({
        @ApiResponse(code = 200, message = "sessão de votação retornada com sucesso"),
        @ApiResponse(code = 404, message = "sessão de votação não encontrada")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sessao> find(@PathVariable Integer id) {

        Sessao sessao = service.find(id);
        return ResponseEntity.ok().body(sessao);
    }

    @ApiOperation(value = "busca todas as sessões de votação")
    @ApiResponses({
        @ApiResponse(code = 200, message = "sessões de votação retornadas com sucesso")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Sessao>> findAll() {

        List<Sessao> sessoes = service.findAll();
        return ResponseEntity.ok().body(sessoes);
    }


}
