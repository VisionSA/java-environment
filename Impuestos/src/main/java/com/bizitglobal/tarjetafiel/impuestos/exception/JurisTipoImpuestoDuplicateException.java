package com.bizitglobal.tarjetafiel.impuestos.exception;
/**
*	Es lanzada cuando existe en la base de datos una actividad con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class JurisTipoImpuestoDuplicateException extends JurisTipoImpuestoException {
	private static final long serialVersionUID = -5610948694723631993L;

	/**
	* Constructor con argumentos para la actividad.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa de la excepción.
	*/
	public JurisTipoImpuestoDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

