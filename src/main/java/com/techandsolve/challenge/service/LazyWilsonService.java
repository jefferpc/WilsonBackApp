/**
 * 
 */
package com.techandsolve.challenge.service;

import java.util.List;

import com.techandsolve.challenge.dto.ViajesRequest;
import com.techandsolve.challenge.dto.ViajesResponse;
import com.techandsolve.challenge.exception.ChallengeException;

/**
 * @author Jperezc
 *
 */
public interface LazyWilsonService {
	

	/**
	 *  leer el archivo y genera uno nuevo con la respuesta
	 *  genera log de ejecucion
	 * @param viajesRequest
	 * @return request
	 * @throws ChallengeException
	 */
	ViajesResponse calcularViajes(ViajesRequest viajesRequest) throws ChallengeException;
	
	/**
	 * calcular la cantidad de los viajes dado el peso de los items
	 * @param pesoItems
	 * @return cantidad de viajes
	 */
	int calcularCantidadViajes(List<Double> pesoItems);

}
