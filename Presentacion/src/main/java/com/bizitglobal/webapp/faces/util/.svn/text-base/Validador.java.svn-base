package com.bizitglobal.webapp.faces.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorEmail;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorTelefono;
import com.bizitglobal.tarjetafiel.proveedores.negocio.TipoVencimiento;


public class Validador {

	public static boolean esEntero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}


	public static boolean esLong(String cadena) {
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}


	public static boolean esFloat(String cadena) {
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}


	public static boolean esNulo(Object unObject) {
		return (unObject == null) ? true : false;
	}


	public static boolean esNuloVacio(String unaCadena) {
		return (esNulo(unaCadena) || unaCadena.equals("")) ? true : false;
	}


	public static boolean emailRequerido(List listaEmail) {
		boolean vacio = false;
		boolean result = false;

		if (!listaEmail.isEmpty()) {
			Iterator emails = listaEmail.iterator();
			while (emails.hasNext() && !vacio) {
				ProveedorEmail email = (ProveedorEmail) emails.next();
				if (email.getEmail().getEmail().equals("") || email.getEmail().getEmail() == null) {
					result = true;
					vacio = true;
				}
			}
		}

		return result;
	}


	public static boolean telefonoRequerido(List listaTelefonos) {
		boolean vacio = false;
		boolean result = false;

		if (!listaTelefonos.isEmpty()) {
			Iterator telefonos = listaTelefonos.iterator();
			while (telefonos.hasNext() && !vacio) {
				ProveedorTelefono telefono = (ProveedorTelefono) telefonos.next();
				if (telefono.getTelefono().getNroTlefono().equals("") || telefono.getTelefono().getNroTlefono() == null) {
					result = true;
					vacio = true;
				}
			}
		}

		return result;
	}


	public static boolean emailInvalido(List listaEmail) {
		boolean encontrado = false;
		boolean result = false;

		if (!listaEmail.isEmpty()) {
			Iterator emails = listaEmail.iterator();
			while (emails.hasNext()) {
				ProveedorEmail email = (ProveedorEmail) emails.next();
				if (!checkEmail(email.getEmail().getEmail()) && !encontrado) {
					result = true;
					encontrado = true;
				}
			}
		}

		return result;
	}


	public static boolean composicionPagoValidar(List listaComposicionPagos) {
		int suma = 0;

		if (!listaComposicionPagos.isEmpty()) {
			Iterator composiciones = listaComposicionPagos.iterator();
			while (composiciones.hasNext()) {
				TipoVencimiento comp = (TipoVencimiento) composiciones.next();
				suma += comp.getPorcentajeMonto().intValue();
			}
		}

		return (suma != 100);
	}


	public static boolean[] composicionPagoValidarCero(List listaComposicionPagos) {
		boolean[] result = { false, false };

		if (!listaComposicionPagos.isEmpty()) {
			Iterator composiciones = listaComposicionPagos.iterator();
			while (composiciones.hasNext()) {
				TipoVencimiento comp = (TipoVencimiento) composiciones.next();
				if (comp.getDias() != null) {
					if (comp.getDias().intValue() < 0) {
						result[0] = true;
					}

					if ((comp.getPorcentajeMonto() == null) || (comp.getPorcentajeMonto().equals(new Integer(0)))) {
						result[1] = true;
					}
				} else {
					result[0] = true;
				}
			}
		}

		return result;
	}


	public static boolean esVacio(List unaLista) {
		return (unaLista.isEmpty()) ? true : false;
	}


	public static boolean checkSinTildes(String cadena) {
		String regex = "([\\w-]+(?:\\.[\\w-]+)*)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cadena);
		return m.matches();

	}


	public static boolean checkEmail(String email) {
		String regex_nueva = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-zA-Z]{2,6}(?:\\.[a-zA-Z]{2})?)$";
		String regex_vieja = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
		String regex_mas_vieja = ".+@.+\\.[a-z]+";
		Pattern p = Pattern.compile(regex_nueva);
		Matcher m = p.matcher(email);
		return m.matches();
	}


	public static boolean checkFecha(Date unaFecha) {
		boolean result = false;

		try {
			Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			Date dt2 = sdf.parse(dateFormat.format(unaFecha));
			result = true;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Fecha incorrecta");
		}

		return result;
	}

}
