/**
 * 
 */
package com.techandsolve.challenge.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.techandsolve.challenge.dto.ViajesRequest;
import com.techandsolve.challenge.dto.ViajesResponse;
import com.techandsolve.challenge.exception.ChallengeException;
import com.techandsolve.challenge.exception.ValidateException;
import com.techandsolve.challenge.model.Log;
import com.techandsolve.challenge.repository.LogRepository;
import com.techandsolve.challenge.service.impl.LazyWilsonServiceImpl;

/**
 * @author Jefferpc
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LazyWilsonServiceTest {

	@Mock
	private LogRepository logRespository;

	@InjectMocks
	private LazyWilsonServiceImpl lazyWilsonService;

	/**
	 * 
	 * @throws ChallengeException
	 */
	@Test(expected = ValidateException.class)
	public void cuandoRequestNullThenLanzaValidaException() throws ChallengeException {

		ViajesRequest request = null;
		lazyWilsonService.calcularViajes(request);

	}

	/**
	 * 
	 * @throws ChallengeException
	 */
	@Test(expected = ValidateException.class)
	public void cuandoFileNullThenLanzaValidaException() throws ChallengeException {

		ViajesRequest request = new ViajesRequest();
		request.setCedula("asdfsad");
		request.setArchivo(null);
		lazyWilsonService.calcularViajes(request);

	}

	/**
	 * 
	 * @throws ChallengeException
	 */
	@Test(expected = ValidateException.class)
	public void cuandoCedulaNullThenLanzaValidaException() throws ChallengeException {

		ViajesRequest request = new ViajesRequest();
		request.setCedula(null);
		request.setArchivo("adsfasd");
		lazyWilsonService.calcularViajes(request);

	}

	@Test
	public void cuandoListaNullReturnCero() {

		int salida = lazyWilsonService.calcularCantidadViajes(null);
		Assert.assertEquals(0, salida);
	}

	@Test
	public void cuandoListDesordencon_1_1_30_30_retorna2() {
		Double[] pesoItems = { 1d, 1d, 30d, 30d };
		List<Double> items = new LinkedList<Double>(Arrays.asList(pesoItems));

		int salida = lazyWilsonService.calcularCantidadViajes(items);
		Assert.assertEquals(2, salida);
	}

	@Test
	public void cuandoNoSumanMasDe50_retorna0() {
		Double[] pesoItems = { 1d, 2d, 3d, 4d, 5d, 6d, 7d };
		List<Double> items = new LinkedList<Double>(Arrays.asList(pesoItems));

		int salida = lazyWilsonService.calcularCantidadViajes(items);
		Assert.assertEquals(0, salida);
	}

	@Test(expected = ChallengeException.class)
	public void cuandoProblemaConArchivoThenLanzaChallengeException() throws ChallengeException {
		Log log = new Log();
		ViajesRequest request = new ViajesRequest();
		request.setCedula("12233");
		request.setArchivo("adsfasd");
		lazyWilsonService.calcularViajes(request);

	}

	@Test
	public void cuandoArchivoOkThenLanzaRetornaArchivo() throws ChallengeException {
		Log log = new Log();

		ViajesRequest request = new ViajesRequest();
		request.setCedula("12233");
		request.setArchivo(
				"NQo0CjMwCjMwCjEKMQozCjIwCjIwCjIwCjExCjEKMgozCjQKNQo2CjcKOAo5CjEwCjExCjYKOQoxOQoyOQozOQo0OQo1OQoxMAozMgo1Ngo3Ngo4CjQ0CjYwCjQ3Cjg1CjcxCjkxCg==");
		ViajesResponse response = lazyWilsonService.calcularViajes(request);

		Assert.assertEquals(0, response.getStatusCode());
		Assert.assertNotEquals("", response.getFile());
		Assert.assertNotEquals(null, response.getFile());
	}

}
