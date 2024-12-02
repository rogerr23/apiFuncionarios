package br.com.roger.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	// Método para criar uma fila no servidor de mensageria do RabbitMQ

	@Bean
	Queue queue() {
		return new Queue("funcionarios", true); // nome da fila
	}

}
