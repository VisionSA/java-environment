package com.bizit.consulta.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.bizit.consulta.web.service.ComercioService;

public class FileUtils {

	/**
	 * Method: getBytesFromFile Description: Obtiene los bytes de un archivo
	 * 
	 * @param file
	 *            archivo
	 * @return bytes[] del archivo
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		// return FileUtils.readFileToByteArray(file);

		InputStream inputStream = new FileInputStream(file);
		byte[] contenido = new byte[(int) file.length()];
		inputStream.read(contenido);
		return contenido;
	}
	
	public static byte[] toByteArrayFromDataInputStream(DataInputStream dis) throws IOException{
		
		if (dis==null){
			throw new IOException("DataInputStream is null");
		}		
		ByteArrayOutputStream baops = new ByteArrayOutputStream();
		try {
			while(true){
				baops.write(dis.readByte());
			}
		}catch (EOFException e){
			baops.close();
			return baops.toByteArray();
		}
	}
	

}
