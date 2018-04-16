/**
 * 
 */
package com.techandsolve.challenge.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techandsolve.challenge.dto.ViajesRequest;
import com.techandsolve.challenge.dto.ViajesResponse;
import com.techandsolve.challenge.exception.ChallengeException;
import com.techandsolve.challenge.model.Log;
import com.techandsolve.challenge.repository.LogRepository;
import com.techandsolve.challenge.service.LazyWilsonService;
import com.techandsolve.challenge.util.ChallengeUtil;
import com.techandsolve.challenge.util.FileUtil;

/**
 * @author Jperezc
 *
 */
@Service
public class LazyWilsonServiceImpl implements LazyWilsonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LazyWilsonServiceImpl.class);
	
	private static final String FORMATO_LINEA="Case #%s: %s\n";
	
	/**
	 * Indica en peso mínino de cada viaje que puede llevar Wilson
	 */
	private static final double PESO_MINIMO_VIAJE = 50;
	
	@Autowired
	private LogRepository logRepository;
	
	
	
	/**
	 * 
	 * @param viajesRequest
	 * @return el archivo con las respustas o una excepcion si algo salio mal
	 * @throws ChallengeException 
	 */
	@Override
	public ViajesResponse calcularViajes(ViajesRequest viajesRequest) throws ChallengeException {
		
		//validar los parametros obligatorios
		ChallengeUtil.isNullOrEmpty(viajesRequest);
		ChallengeUtil.isNullOrEmpty(viajesRequest.getCedula(),viajesRequest.getArchivo());
		
		
		ViajesResponse response=new ViajesResponse();
		
		try {
			generateLog(viajesRequest);
			List<List<Double>> items=ChallengeUtil.getItemsFromFile(viajesRequest.getArchivo());
			StringBuilder salida=new StringBuilder("");
			for (int i = 0; i < items.size(); i++) {
				salida.append(String.format(FORMATO_LINEA, i+1,calcularCantidadViajes(items.get(i))));
			}
			response.setFile(FileUtil.encodeFile(salida.toString()));
		}catch (ChallengeException e) {
			LOGGER.error("Error calculando viajes.", e);
			throw e;
		} 
		catch (Exception e) {
			LOGGER.error("Error leyendo el archivo.", e);
			throw new ChallengeException("Error leyendo el archivo.",e);
		}
		
		return response;
	}

	/**
	 * Obtiene la cantidad maxima de viajes que puede realizar wilson
	 * 
	 * @param pesoItems
	 *            Indica el peso de cada uno de los Items
	 * @return
	 */
	@Override
	public int calcularCantidadViajes(List<Double> pesoItems) {
		
		if(pesoItems==null) {
			return 0;
		}
		
		int cantItemsLibres = pesoItems.size();
		int cantidadViajes = 0;
		double cantItemsRequeridos = 0;

		// ordenar items de mayor a menor
		pesoItems.sort((Double o1, Double o2) -> (-1) * o1.compareTo(o2));
		for (Double pesoItem : pesoItems) {
			// se calcula y se aproxima al entero mayor
			cantItemsRequeridos = Math.ceil(PESO_MINIMO_VIAJE / pesoItem);
			// si no hay suficientes items
			if (cantItemsRequeridos > cantItemsLibres || cantItemsLibres == 0) {
				break;
			}

			cantItemsLibres -= cantItemsRequeridos;
			cantidadViajes++;
		}
		return cantidadViajes;
	}
	
	/**
	 * generar log de la ejecucion
	 * @param viajesRequest
	 */
	private void generateLog(ViajesRequest viajesRequest) {
		Log log= new Log();
		log.setCedula(viajesRequest.getCedula());
		log.setFechaRegistro(new Date());
		logRepository.save(log);
	}

}
