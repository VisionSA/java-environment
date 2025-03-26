package com.bizitglobal.webapp.faces.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


/**
 * Administrador de transacciones, se encarga de crear las transacciones y tomar los tiempos de ejecucion para cada transaccion.
 * 
 * @author BizitDesarrollo
 */
public class SocketCliente extends Socket implements Runnable {
	// private static final Logger log = Logger.getLogger(SocketCliente.class);

	int numero;
	Socket conexion;
	String token = "";
	BufferedReader in; // flujo de entrada, lo que nos envia el servidor al cliente.
	PrintWriter out; // flujo de salida, lo que envia el cliente al servidor.
	StringBuffer result;


	public SocketCliente(int numero, Socket conexion, String token, StringBuffer result) {
		this.result = result;
		this.numero = numero;
		this.conexion = conexion;
		this.token = token;
		try {
			conexion.setSoTimeout(6000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void run() {
		try {

			in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			out = new PrintWriter(conexion.getOutputStream(), true);
			String respuesta = "";
			long tiempoInicial = System.currentTimeMillis();
			out.println(token);
			int i = 0;
			while (true) {
				i++;
				if (System.currentTimeMillis() - tiempoInicial > 4000) {
					System.out.println("Tiempo de espera superado (4 segundos)");
					result.append("Tiempo de espera superado (4 segundos) \n");
					break;
				}
				// Thread.sleep(20);
				respuesta = in.readLine();
				long tiem = System.currentTimeMillis() - tiempoInicial;
				System.out.println("La respuesta dada al sokete numero " + numero + " se demoró " + tiem);
				result.append("La respuesta dada al sokete numero " + numero + " se demoró " + tiem + " \n");
				if (respuesta != null) {
					break;
				}
			}
			System.out.println(respuesta);
			result.append("Respuesta -> " + respuesta + " \n");
			conexion.close();
			System.out.println("Conexion cerrada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Socket getConexion() {
		return conexion;
	}


	public void setConexion(Socket conexion) {
		this.conexion = conexion;
	}

}
