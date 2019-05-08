package com.almundo.supervisores;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

@EnableJms
@SpringBootApplication
public class SupervisoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupervisoresApplication.class, args);
	}
	
	@Bean
    JmsListenerContainerFactory<?> myJmsContainerFactorySup(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        
        return factory;
    }

}
