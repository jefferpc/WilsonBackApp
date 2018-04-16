/**
 * 
 */
package com.techandsolve.challenge.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techandsolve.challenge.dto.BaseResponse;
import com.techandsolve.challenge.dto.ViajesRequest;
import com.techandsolve.challenge.exception.ChallengeException;
import com.techandsolve.challenge.exception.ValidateException;
import com.techandsolve.challenge.service.LazyWilsonService;

/**
 * @author Jperezc
 *
 */
@RestController
@RequestMapping("/viajes")
public class LazyWilsonController {

	@Autowired
	private LazyWilsonService lazyWilsonService;

	/**
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse getViajes(HttpServletResponse response, @RequestBody ViajesRequest request) throws IOException {
		BaseResponse viajesResponse = new BaseResponse();

		try {
			return lazyWilsonService.calcularViajes(request);
		} catch (ValidateException e) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
			viajesResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			viajesResponse.setStatusMessage(e.getMessage());
		} catch (ChallengeException e) {
			response.sendError(HttpStatus.CONFLICT.value(), e.getMessage());
			viajesResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			viajesResponse.setStatusMessage(e.getMessage());
		} catch (Exception e) {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
			viajesResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			viajesResponse.setStatusMessage(e.getMessage());

		}

		return viajesResponse;

	}

}
