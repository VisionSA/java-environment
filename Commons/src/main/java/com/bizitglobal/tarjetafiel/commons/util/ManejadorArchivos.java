package com.bizitglobal.tarjetafiel.commons.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;

public class ManejadorArchivos {

	private static final Logger log = Logger.getLogger(ManejadorArchivos.class);

	public static void crearArchivo(StringBuffer path, StringBuffer name)
			throws IOException {
		File file = new File(path.toString(), name.toString());
		if (file.createNewFile()) {
			log.info("El archivo " + name + " se creo correctamente");
		} else {
			log.error("El archivo " + name + " NO se ha podido crear");
		}
	}

	public static void escribirArchivo(StringBuffer path, StringBuffer name,
			StringBuffer contents) throws IOException {
		
		File file = new File(path.toString(), name.toString());
		
		if (!file.exists()) {
			throw new FileNotFoundException("File does not exist: " + file);
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: "
					+ file);
		}
		if (!file.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: "
					+ file);
		}

		// use buffering
		Writer output = new BufferedWriter(new FileWriter(file));
		try {
			// FileWriter always assumes default encoding is OK!
			output.write(contents.toString());
		} finally {
			output.close();
		}
	}

}
