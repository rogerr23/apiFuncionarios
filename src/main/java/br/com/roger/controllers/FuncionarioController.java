package br.com.roger.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roger.dtos.FuncionarioRequestDto;
import br.com.roger.dtos.FuncionarioResponseDto;
import br.com.roger.services.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	public FuncionarioResponseDto post(@RequestBody FuncionarioRequestDto dto) {
		return funcionarioService.criar(dto);

	}

	@PutMapping({ "id" })
	public FuncionarioResponseDto put(@PathVariable UUID id, @RequestBody FuncionarioRequestDto dto) {
		return funcionarioService.alterar(id, dto);
	}

	@DeleteMapping({ "id" })
	public FuncionarioResponseDto delete(@PathVariable UUID id) {
		return funcionarioService.excluir(id);

	}

	@GetMapping("{dataMin}/{dataMax}")
	public List<FuncionarioResponseDto> getByDatas(@PathVariable Date dataMin, @PathVariable Date dataMax) {
		return funcionarioService.consultar(dataMin, dataMax);
	}

	@GetMapping({ "id" })
	public FuncionarioResponseDto getById(@PathVariable UUID id) {
		return funcionarioService.obterPorId(id);
	}

}
