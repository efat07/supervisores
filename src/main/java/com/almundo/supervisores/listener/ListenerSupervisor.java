package com.almundo.supervisores.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.almundo.supervisores.constantes.Constante;
import com.almundo.supervisores.model.Llamada;
import com.almundo.supervisores.model.Supervisor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

@Component
public class ListenerSupervisor {
	
	ObjectMapper mapper = new ObjectMapper();
	Llamada llamada = null;
	
	@JmsListener(destination = Constante.nombreColaSupervisor, containerFactory = Constante.nombreFactory)
	public void onMessage( final Message msgLlamadaIn ) throws JMSException {
		
		try {
			System.out.println("Llamada Recibida por el Supervisor: ");
			TextMessage msgLlamada = (TextMessage) msgLlamadaIn;
			System.out.println( msgLlamada.getText() );
			
			llamada = mapper.readValue(msgLlamada.getText(), Llamada.class);
			Supervisor supervisor = Supervisor.getInstanciaSingleton(1, llamada);
			supervisor.correrTiempo();
			System.out.println("Pasaron " + supervisor.verificaDisponibilidadSegundo() + " segundos");
			System.out.println("________________________________________________");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
