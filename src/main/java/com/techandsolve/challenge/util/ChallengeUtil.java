/**
 * 
 */
package com.techandsolve.challenge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.techandsolve.challenge.exception.ChallengeException;
import com.techandsolve.challenge.exception.ValidateException;

/**
 * @author Jperezc
 *
 */
public class ChallengeUtil {
	/**
	 * 
	 */
	private ChallengeUtil() {

	}

	private static final String PARAMETRO_NULO = "Párametro nulo.";

	/**
	 * 
	 * @param string
	 * @return
	 * @throws ValidateException
	 */
	public static boolean isNullOrEmptyString(String string) throws ValidateException {
		if (string == null || string.trim().isEmpty()) {
			throw new ValidateException(PARAMETRO_NULO);
		}
		return false;
	}

	/**
	 * 
	 * @param object
	 * @return
	 * @throws ValidateException
	 */
	public static boolean isNullObject(Object object) throws ValidateException {
		if (object == null) {
			throw new ValidateException(PARAMETRO_NULO);
		}
		return false;
	}

	/**
	 * 
	 * @param objects
	 * @return
	 * @throws ValidateException
	 */
	public static boolean isNull(Object... objects) throws ValidateException {
		for (Object object : objects) {
			isNullObject(object);
		}
		return false;
	}

	/**
	 * Valida cada uno de los parámetros lanza una excepción en caso de que alguno
	 * sea nulo. Si el objeto es un String valida que no sea vacio.
	 * 
	 * @param objects
	 * @return
	 * @throws ValidateException
	 */
	public static boolean isNullOrEmpty(Object... objects) throws ValidateException {
		for (Object object : objects) {

			if (object instanceof String) {
				isNullOrEmptyString((String) object);
			} else {
				isNullObject(object);
			}

		}
		return false;
	}

	/**
	 * Carga la estructure del archivo en un lista de lista de itesm por cada dia
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ChallengeException
	 */
	public static List<List<Double>> getItemsFromFile(String file) throws IOException, ChallengeException {
		// leer archivo

		List<List<Double>> items = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(FileUtil.getFileFromBase64(file)));) {

			List<Double> itemsDia;
			String linea = input.readLine();
			int cantDias = Integer.parseInt(linea);
			int cantItems;
			for (int i = 0; i < cantDias; i++) {
				cantItems = Integer.parseInt(input.readLine());
				itemsDia = new ArrayList<>();
				for (int j = 0; j < cantItems; j++) {
					linea = input.readLine();
					if (linea == null) {
						throw new ChallengeException("Archivo inconsistente.");
					}
					itemsDia.add(Double.parseDouble(linea));
				}
				items.add(itemsDia);
			}
		}
		return items;
	}
	

}
