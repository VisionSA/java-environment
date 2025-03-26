package com.bizitglobal.webapp.faces.beans.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;


public class MergePDF {
	/**
	 * @param PDFFiles
	 *            Lista de archivos pdf a unir
	 * @param outputFile
	 *            archivo usado para unir los pdf
	 */
	public static void concatPDFs(List<PdfReader> PDFFiles, String outputFile) {
		PdfCopyFields copy = null;
		try {

			Iterator<PdfReader> iteratorPDFs = PDFFiles.iterator();
			copy = new PdfCopyFields(new FileOutputStream(outputFile));
			while (iteratorPDFs.hasNext()) {
				PdfReader pdf = iteratorPDFs.next();
				copy.addDocument(pdf);
			}
			copy.close();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			copy.close();
		}

	}

}
