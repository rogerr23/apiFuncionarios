package br.com.roger.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.roger.entities.Funcionario;

@Component
public class MessageProducerComponent {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Queue queue;

	// Método para enviar os dados do funcionário para a fila do RabbitMQ

	public void send(Funcionario funcionario) throws Exception {

		// convertendo os dados para JSON
		var json = objectMapper.writeValueAsString(funcionario);

		// enviando os dados para a fila da mensageria (queue 'funcionarios')
		rabbitTemplate.convertAndSend(queue.getName(), json);
	}

}
