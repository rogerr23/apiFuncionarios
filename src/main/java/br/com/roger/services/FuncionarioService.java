package br.com.roger.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roger.components.MessageProducerComponent;
import br.com.roger.dtos.FuncionarioRequestDto;
import br.com.roger.dtos.FuncionarioResponseDto;
import br.com.roger.entities.Funcionario;
import br.com.roger.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageProducerComponent messageProducerComponent;

	public FuncionarioResponseDto criar(FuncionarioRequestDto dto) {

		var funcionario = modelMapper.map(dto, Funcionario.class);
		funcionario.setId(UUID.randomUUID());

		funcionarioRepository.save(funcionario);

		try {
			messageProducerComponent.send(funcionario);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return modelMapper.map(funcionario, FuncionarioResponseDto.class);

	}

	public FuncionarioResponseDto alterar(UUID id, FuncionarioRequestDto dto) {

		var funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado."));

		funcionario.setNome(dto.getNome());
		funcionario.setCpf(dto.getCpf());
		funcionario.setEmail(dto.getEmail());
		funcionario.setMatricula(dto.getMatricula());
		funcionario.setDataAdmissao(dto.getDataAdmissao());
		funcionario.setSalario(dto.getSalario());

		funcionarioRepository.save(funcionario);

		return modelMapper.map(funcionario, FuncionarioResponseDto.class);

	}

	public FuncionarioResponseDto excluir(UUID id) {

		var funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado."));

		funcionarioRepository.delete(funcionario);

		return modelMapper.map(funcionario, FuncionarioResponseDto.class);

	}

	public List<FuncionarioResponseDto> consultar(Date dataMin, Date dataMax) {

		var response = new ArrayList<FuncionarioResponseDto>();

		var funcionarios = funcionarioRepository.findByDatas(dataMin, dataMax);

		for (var funcionario : funcionarios) {
			response.add(modelMapper.map(funcionario, FuncionarioResponseDto.class));
		}

		return response;
	}

	public FuncionarioResponseDto obterPorId(UUID id) {

		var funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado."));

		return modelMapper.map(funcionario, FuncionarioResponseDto.class);

	}

}
