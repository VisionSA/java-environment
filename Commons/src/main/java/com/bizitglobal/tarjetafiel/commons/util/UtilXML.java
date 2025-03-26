package com.bizitglobal.tarjetafiel.commons.util;

import java.io.ByteArrayOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class UtilXML {

	public String document2String(Document doc) {
		String xml = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			xml = out.toString();
			out.close();

		} catch (Exception e) {
		}
		return xml;
	}
}
