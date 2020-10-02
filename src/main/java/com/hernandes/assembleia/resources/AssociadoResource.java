package com.hernandes.assembleia.resources;

import com.hernandes.assembleia.domain.Associado;
import com.hernandes.assembleia.dto.AssociadoDTO;
import com.hernandes.assembleia.services.AssociadoService;
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
@RequestMapping(value = "/associados")
public class AssociadoResource {

    @Autowired
    private AssociadoService service;

    @ApiOperation(value = "inclusão de associado")
    @ApiResponses({
        @ApiResponse(code = 201, message = "associado criado com sucesso")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AssociadoDTO associadoDto) {

        Associado associado = service.fromDTO(associadoDto);
        associado = service.insert(associado);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(associado.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "busca um associado")
    @ApiResponses({
        @ApiResponse(code = 200, message = "associado retornado com sucesso"),
        @ApiResponse(code = 404, message = "associado não encontrado")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Associado> find(@PathVariable Integer id) {

        Associado associado = service.find(id);
        return ResponseEntity.ok().body(associado);
    }

    @ApiOperation(value = "busca todos os associados")
    @ApiResponses({
        @ApiResponse(code = 200, message = "associados retornados com sucesso")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Associado>> findAll() {

        List<Associado> associados = service.findAll();
        return ResponseEntity.ok().body(associados);
    }

}
