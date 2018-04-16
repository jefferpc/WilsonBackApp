/**
 * 
 */
package com.techandsolve.challenge.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Jperezc
 *
 */
public  class FileUtil {
	
	private FileUtil() {
		
	}
	/**
	 * convierte un archivo codificado en base64 a un inputstream
	 * @param fileEncoded
	 * @return null si la entreda es nula o el InputStream decodificado
	 */
	public static final InputStream getFileFromBase64(String fileEncoded) {
		if(fileEncoded==null) {
			return null;
		}
		byte[] decoded = Base64.getDecoder().decode(fileEncoded);
		
		return new ByteArrayInputStream(decoded);
	}
	/**
	 * codifica en base 64 el contenido del archivo
	 * @param content
	 * @return el string codificado
	 */
	public static String encodeFile(String content) {
		byte[] encoded = Base64.getEncoder().encode(content.getBytes(StandardCharsets.UTF_8));
		return new String(encoded);
	}

}
