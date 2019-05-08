package com.almundo.supervisores.model;

import java.util.concurrent.TimeUnit;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

public class Supervisor {

	private Integer supervisorId = null;
	private Long tiempoLlamada = 0L;
	private Long tiempoInicio = 0L;
	private Long tiempoFinal = 0L;
	private Llamada llamada = null;
	private static Supervisor supervisor;
	
	private Supervisor() {
	}
	
	private Supervisor(Integer supervisorId, Llamada llamada) {
		this.supervisorId = supervisorId;
		this.llamada = llamada;
	}
	
	public static Supervisor getInstanciaSingleton() {
		if(supervisor == null) {
			supervisor = new Supervisor();
		}
		return supervisor;
	}
	
	public static Supervisor getInstanciaSingleton(Integer supervisorId, Llamada llamada) {
		if(supervisor == null) {
			supervisor = new Supervisor(supervisorId,llamada);
		}
		return supervisor;
	}
	
	public void correrTiempo() {
		try {
			tiempoInicio = System.nanoTime();
			tiempoFinal  = 0L;
			tiempoLlamada = getNumeroAleatorioEntreDosNumeros(5,10);
			TimeUnit.SECONDS.sleep(tiempoLlamada);
			tiempoFinal = System.nanoTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int verificaDisponibilidadSegundo() {
		long output = tiempoFinal - tiempoInicio;
		return (int) ((output / 1000000)/1000);
	}
	
	private long getNumeroAleatorioEntreDosNumeros(int min, int max) {
		max=(max)+1;
        long resultado = ((long)(Math.random()*(max-min))+min);
		return resultado;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}
	public Llamada getLlamada() {
		return llamada;
	}
	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

	public Long getTiempoLlamada() {
		return tiempoLlamada;
	}

	public void setTiempoLlamada(Long tiempoLlamada) {
		this.tiempoLlamada = tiempoLlamada;
	}
	
	public Long getTiempoInicio() {
		return tiempoInicio;
	}
	
	public void setTiempoInicio(Long tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public Long getTiempoFinal() {
		return tiempoFinal;
	}
	
	public void setTiempoFinal(Long tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}
}
