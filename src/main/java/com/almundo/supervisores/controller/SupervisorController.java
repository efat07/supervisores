package com.almundo.supervisores.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.supervisores.model.Supervisor;
import com.almundo.supervisores.payload.SupervisorResponse;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

@RestController
@CrossOrigin(origins = "*")
public class SupervisorController {

    @GetMapping("/validarDispSupervisor")
    public ResponseEntity<?> validarDisponibilidad() {
        int segundosDemora = 0;
    	Supervisor supervisor = Supervisor.getInstanciaSingleton();
    	segundosDemora = supervisor.verificaDisponibilidadSegundo();
    	int tiempoLlamada = Integer.parseInt(supervisor.getTiempoLlamada().toString());
        if(segundosDemora >= tiempoLlamada) {
        	segundosDemora = 0;
        	supervisor.setTiempoInicio(0L);
        	supervisor.setTiempoFinal(0L);
        }else {
        	segundosDemora = tiempoLlamada - segundosDemora;
        	segundosDemora = Math.abs(segundosDemora);
        }
        
        int llamadasEnCola = contarLlamadasEnCola();
        
    	SupervisorResponse supervisorResponse = new SupervisorResponse();
    	if(supervisor.getLlamada() != null) {
    		supervisorResponse.setIdLlamada(supervisor.getLlamada().getLlamadaId());
    	}
    	supervisorResponse.setDuracionAtendiendo(segundosDemora);
    	supervisorResponse.setCantidadEnCola(llamadasEnCola);
    	return ResponseEntity.ok(supervisorResponse);
    }
    
    private int contarLlamadasEnCola() {
		int resultado = 0;
		//Realizar el conteo de los mensajes en la cola de mensajeria que representaría la cantidad de llamadas en espera
		return resultado;
	}
}