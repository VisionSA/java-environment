package com.bizitglobal.tarjetafiel.commons.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AlgoritmoEncriptacionAES {

	private static String ALGORITHM = "AES";
	private static String INSTANCE = "AES/CBC/PKCS5Padding";
	private static String CHARACTER_SET = "UTF8";

	private String keyValue = "keypassbizit2008";
	private String ivValue = "keypassbizit2008";

	public AlgoritmoEncriptacionAES() {
	}

	public AlgoritmoEncriptacionAES(String password, String iv) {
		keyValue = password;
		ivValue = iv;
	}

	public String encriptar(String valorEncriptar) throws Exception {
		SecretKeySpec clave = generarClave();
		IvParameterSpec iv = generarIv();
		Cipher c = Cipher.getInstance(INSTANCE);
		c.init(Cipher.ENCRYPT_MODE, clave, iv);
		byte[] encValor = c.doFinal(valorEncriptar.getBytes(CHARACTER_SET));
		String valorEncriptado = new BASE64Encoder().encode(encValor);
		return valorEncriptado;
	}

	public String encriptarURLEncoder(String valorEncriptar) throws Exception {
		return URLEncoder.encode(encriptar(valorEncriptar), CHARACTER_SET);
	}

	public String desencriptar(String valorEncriptado) throws Exception {
		SecretKeySpec clave = generarClave();
		IvParameterSpec iv = generarIv();
		Cipher c = Cipher.getInstance(INSTANCE);
		c.init(Cipher.DECRYPT_MODE, clave, iv);
		byte[] valorDecodificado = new BASE64Decoder()
				.decodeBuffer(valorEncriptado);
		byte[] decValor = c.doFinal(valorDecodificado);
		String valorDesencriptado = new String(decValor, CHARACTER_SET);
		return valorDesencriptado;
	}

	public String desencriptarURLDecoder(String valorEncriptado)
			throws Exception {
		return desencriptar(URLDecoder.decode(valorEncriptado, CHARACTER_SET));
	}

	private SecretKeySpec generarClave() throws Exception {
		SecretKeySpec clave = new SecretKeySpec(keyValue.getBytes(), ALGORITHM);
		return clave;
	}

	private IvParameterSpec generarIv() throws Exception {
		IvParameterSpec clave = new IvParameterSpec(ivValue.getBytes());
		return clave;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getIvValue() {
		return ivValue;
	}

	public void setIvValue(String ivValue) {
		this.ivValue = ivValue;
	}

}
