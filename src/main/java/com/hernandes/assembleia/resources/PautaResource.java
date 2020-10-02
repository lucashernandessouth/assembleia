package com.hernandes.assembleia.resources;

import com.hernandes.assembleia.domain.Pauta;
import com.hernandes.assembleia.dto.PautaDTO;
import com.hernandes.assembleia.services.PautaService;
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
@RequestMapping(value = "/pautas")
public class PautaResource {

    @Autowired
    private PautaService service;

    @ApiOperation(value = "inclusão de pauta")
    @ApiResponses({
        @ApiResponse(code = 201, message = "pauta criada com sucesso")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PautaDTO pautaDto) {

        Pauta pauta = service.fromDTO(pautaDto);
        pauta = service.insert(pauta);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pauta.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "busca uma pauta")
    @ApiResponses({
        @ApiResponse(code = 200, message = "pauta retornada com sucesso"),
        @ApiResponse(code = 404, message = "pauta não encontrada")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pauta> find(@PathVariable Integer id) {

        Pauta pauta = service.find(id);
        return ResponseEntity.ok().body(pauta);
    }

    @ApiOperation(value = "busca todas as pautas")
    @ApiResponses({
        @ApiResponse(code = 200, message = "pautas retornadas com sucesso")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pauta>> findAll() {

        List<Pauta> pautas = service.findAll();
        return ResponseEntity.ok().body(pautas);
    }

}
