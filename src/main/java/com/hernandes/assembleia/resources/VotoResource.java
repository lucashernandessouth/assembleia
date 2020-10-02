package com.hernandes.assembleia.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hernandes.assembleia.domain.Voto;
import com.hernandes.assembleia.dto.VotoDTO;
import com.hernandes.assembleia.services.VotoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/votos")
public class VotoResource {

	@Autowired
	private VotoService service;
	
	@ApiOperation(value = "inclusão de voto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "voto computado com sucesso"),
		@ApiResponse(code = 400, message = "pauta fechada ou sem sessão de votação/voto inválido/associado já votou nessa pauta"),
		@ApiResponse(code = 404, message = "pauta ou associado inexistente.")
	})
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody VotoDTO votoDto){
		
		Voto voto = service.fromDTO(votoDto);
		voto = service.insert(voto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(voto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}	
	
	@ApiOperation(value = "busca todos os votos")
	@ApiResponses({
		@ApiResponse(code = 200, message = "votos retornados com sucesso")
	})
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Voto>> findAll() {
		
		List<Voto> votos = service.findAll();
		return ResponseEntity.ok().body(votos);		
	}
	
}
