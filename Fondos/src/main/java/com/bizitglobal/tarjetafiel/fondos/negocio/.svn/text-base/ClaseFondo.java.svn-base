package com.bizitglobal.tarjetafiel.fondos.negocio;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.exception.ClaseFondoException;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

/**
 * @author hernan
 * 1 Cobros                                             
 * 2 Pagos                                              
 * 3 Depósitos                                          
 * 4 Otros movimientos de bancos y carteras             
 * 5 Rechazos de cheques propios                        
 * 6 Rechazos de cheques de terceros                    
 * 7 Otros movimientos    (ESTA NO LA VAMOS A UTILIZAR. EN REEMPLAZO SE USA LA 4)                              
 * 8 Transferencia de cheques diferidos a banco         
 * 9 Transferencia entre carteras                       
 *
 */
public class ClaseFondo  implements Negocio {
	public final static int DEBITA		= 1;
	public final static int ACREDITA	= -1;
	
	// Clases
	public final static int COBROS 								= 1;
	public final static int PAGOS 								= 2;
	public final static int DEPOSITOS 							= 3;
	public final static int OTROS_MOV_BANCOS_Y_CARTERAS 		= 4;
	public final static int RECHAZOS_DE_CHEQUES_PROPIOS 		= 5;
	public final static int RECHAZOS_DE_CHEQUES_TERCEROS 		= 6;
//	public final static int OTROS_MOVIMIENTOS 					= 7;
	public final static int TRANSF_CHEQUES_DIFERIDOS_BANCO 		= 8;
	
	// Tipos de medios
	public final static int MEDIO_OTROS				= 1; // Este tambien representa un importe directo
	public final static int MEDIO_EFECTIVO			= 1; // Este tambien representa un importe directo
	public final static int MEDIO_CHEQUE_PROPIO		= 2;
	public final static int MEDIO_CHEQUE_TERCERO	= 3;
	public final static int MEDIO_ACREDITACION 		= 4;
	public final static int MEDIO_DEPOSITO			= 5;
	
	// Tipos de Cuentas
	public final static int OTROS 	= 1; // Cuentas que representan valores en efectivo
	public final static int BANCOS 	= 2; // Cuentas que representan cuentas corrientes y que emiten cheques
	public final static int CARTERA = 4; // Cuentas que representan cheques de terceros
	
	private int clase = 0;
	private int signoCuentaUnica = 0;
	private ConceptoGen concepto;
	private List list;
	private final static List listaAcciones = new ArrayList();
	private final static String[] claseString = 
			{"Cobros",                                            
		 	"Pagos",                                              
		 	"Depósitos",                                          
		 	"Otros movimientos de bancos y carteras",             
		 	"Rechazos de cheques propios",           
		 	"Rechazos de cheques de terceros",                    
		 	"Otros movimientos",                 
		 	"Transferencia de cheques diferidos a banco",         
		 	"Transferencia entre carteras"};
    
    public ClaseFondo() {
    	
	}

	public ClaseFondo(ConceptoGen concepto) {
		this.concepto = concepto;
		this.clase = concepto.getTipoConcepto().getIdTipoConcepto().intValue();
	}
    
	public Long getId() {
    	return null;
    }
    
    public String getLabel() {
    	return null;
    }
    
    public int getClase() {
		return clase;
	}

	public void setClase(int clase) {
		signoCuentaUnica = 0;
		this.clase = clase;
	}

	public Integer getSignoCuentaUnica() {
		return signoCuentaUnica;
	}

	public void setSignoCuentaUnica(Integer signoCuentaUnica) {
		this.signoCuentaUnica = signoCuentaUnica;
	}
	
	public String getDescripcion(){
		return (clase == 0)?"Sin Datos":claseString[clase - 1];
	}

	public List getSelectItems(){
    	if (list == null) {
			list = new ArrayList();
			list.add(new SelectItem(new Long(0),"Seleccione una Clase"));
			list.add(new SelectItem(new Long(COBROS),"Cobros"));
			list.add(new SelectItem(new Long(PAGOS),"Pagos"));
			list.add(new SelectItem(new Long(DEPOSITOS),"Depósitos"));
			list.add(new SelectItem(new Long(OTROS_MOV_BANCOS_Y_CARTERAS),"Otros movimientos de bancos y carteras"));
			list.add(new SelectItem(new Long(RECHAZOS_DE_CHEQUES_PROPIOS),"Rechazos de cheques propios"));
			list.add(new SelectItem(new Long(RECHAZOS_DE_CHEQUES_TERCEROS),"Rechazos de cheques de terceros"));
//			list.add(new SelectItem(new Long(OTROS_MOVIMIENTOS),"Otros movimientos"));
			list.add(new SelectItem(new Long(TRANSF_CHEQUES_DIFERIDOS_BANCO),"Transferencia de cheques diferidos a banco"));
		}
    	return list;
    }
    
    /**
     * @param cuenta
     * @param signo : true = debe / false = haber
     * @return un array con el comportamiento resuelto
     * En la pocicion 0: Integer con medio que corresponde a la cuenta
     * 		IMPORTE_DIRECTO	= 1
     * 		CHEQUE_PROPIO	= 2
	 * 		CHEQUE_TERCERO	= 3
	 *		ACREDITACION 	= 4
	 *		DEPOSITO		= 5
     * En la pocicion 1: Boolean que indica si es un alta(true) o una busqueda(false)
     * 						Null, significa a eleccion del usuario // O no se aplica ninguna de las dos(segun el medio)
     */
    public Object[] accionesDisponibles(PlanCuentaDos cuenta, boolean signo) throws ClaseFondoException{
    	Object[] resul = new Object[2];
    	if(cuenta.getTipoFondos() == null){
			resul[0] = null; 
			resul[1] = null;
		}else{
			int formaPago = cuenta.getFormaPago().getIdFormaPago().intValue();
			if (formaPago == FormaPago.OTROS) {
				resul[0] = new Integer(MEDIO_OTROS);
				resul[1] = null;
			} else {
			}
			
			switch (clase) {
			case COBROS:
				if (signo) { // DEBE
					switch (cuenta.getTipoFondos().intValue()) {
					case CARTERA:
						/* En este caso solo se puede cargar cheques de terceros*/
//							2 solo Alta cheque tercero
						if (formaPago == FormaPago.CHEQUE){
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(true);
						}
						break;
					case BANCOS:
//							segunn el medio:
//								6 por importe directo
						if (formaPago == FormaPago.DEPOSITO_BANCARIO){
							resul[0] = new Integer(MEDIO_DEPOSITO);  // solo cargar un monto
							resul[1] = new Boolean(true);
						}else{
//								2 Solo busqueda
							if (formaPago == FormaPago.CHEQUE) {
								resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
								resul[1] = new Boolean(false);
							}
						}
						break;
					case OTROS:
						/* En este caso solo se puede hacer un importe directo*/
//							1 y 5
						if (formaPago == FormaPago.EFECTIVO || formaPago == FormaPago.TICKETS) {
							resul[0] = new Integer(MEDIO_EFECTIVO);  // O CUASI MONEDAS (tikets)
							resul[1] = null;
						}
						break;
					}
					
				} else { // HABER
					if (formaPago == FormaPago.OTROS) {
						resul[0] = new Integer(MEDIO_OTROS);
						resul[1] = null;
					}		
				}
				break;
			case PAGOS:
				if (signo) { // DEBE
					if (formaPago == FormaPago.OTROS) {
						resul[0] = new Integer(MEDIO_OTROS);
						resul[1] = null;
					}
				} else { // HABER
					switch (cuenta.getTipoFondos().intValue()) {
					case CARTERA:
//						2 busqueda
						/* En este caso solo se puede buscar cheques de terceros*/
						if (formaPago == FormaPago.CHEQUE){
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(false);
						}
						break;
					case BANCOS:
						/* En este caso se emitiran cheques o se imputa de forma directa*/
//						2, 3 alta
//						6 importe directo
						if (formaPago == FormaPago.CHEQUE) {//Cheque
							resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
							resul[1] = new Boolean(true);	
						}else {
							if (formaPago == FormaPago.ACREDITACION_BANCARIA) {
								resul[0] = new Integer(MEDIO_ACREDITACION); 
								resul[1] = new Boolean(true);	
							}else{
								if (formaPago == FormaPago.DEPOSITO_BANCARIO) {
									resul[0] = new Integer(MEDIO_DEPOSITO); 
									resul[1] = null;
								}
							}
						}
						break;
					case OTROS:
						/* En este caso solo se puede hacer un importe directo*/
//						1 y 5
						if (formaPago == FormaPago.EFECTIVO || formaPago == FormaPago.TICKETS) {
							resul[0] = new Integer(MEDIO_EFECTIVO);  // O CUASI MONEDAS (tikets)
							resul[1] = null;
						}
						break;
					}
				}
				break;
			case DEPOSITOS:
				if (signo) { // DEBE
					switch (cuenta.getTipoFondos().intValue()) {
					case BANCOS:
						if (formaPago == FormaPago.DEPOSITO_BANCARIO) { // cambiar a cuenta banco
							resul[0] = new Integer(MEDIO_DEPOSITO); 
							resul[1] = null;
						}
						break;
					}
				} else { // HABER
					switch (cuenta.getTipoFondos().intValue()) {
					case CARTERA:
//						2 busqueda
						/* En este caso solo se puede buscar cheques de terceros en cartera*/
						if (formaPago == FormaPago.CHEQUE){
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(false);
						}
						break;
					case BANCOS:
						/* Nunca se puede usar una cuenta banco aca*/
						resul[0] = null; 
						resul[1] = null;			
						break;
					case OTROS:
//						1 y 5 
						/* En este caso solo se puede hacer un importe directo*/
						if (formaPago == FormaPago.EFECTIVO || formaPago == FormaPago.TICKETS) {
							resul[0] = new Integer(MEDIO_EFECTIVO);  // O CUASI MONEDAS (tikets)
							resul[1] = null;
						}
						break;
					}
				}
				break;
			case OTROS_MOV_BANCOS_Y_CARTERAS:
//					A partir del debe se comporta:
//						1 - 1,5
//		    		segun el tipo y el medio es lo que permitis hacer
//		    		condicionar a partir de la cuenta unica
				if (signo) { // DEBE
					switch (cuenta.getTipoFondos().intValue()) {
					case CARTERA:
						/* En este caso solo se puede buscar cheques de terceros en cartera*/
						if (formaPago == FormaPago.CHEQUE) {//Cheque
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(true);
						}
						break;
					case BANCOS:
						/* En este caso se emitiran cheques o se creara una acreditacion o importe directo*/
						if (formaPago == FormaPago.ACREDITACION_BANCARIA) {//Acrditacion
							resul[0] = new Integer(MEDIO_ACREDITACION); 
							resul[1] = new Boolean(false);
						}else {
							if (formaPago == FormaPago.CHEQUE) {//Cheque
								resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
								resul[1] = new Boolean(false);
							}else {
								if (formaPago == FormaPago.DEPOSITO_BANCARIO){
									resul[0] = new Integer(MEDIO_DEPOSITO); 
									resul[1] = null;
								}
							}
						}
						break;
					case OTROS:
						/* En este caso solo se puede hacer un importe directo*/
						if (formaPago == FormaPago.EFECTIVO || formaPago == FormaPago.TICKETS || formaPago == FormaPago.TARJETA_DEBITO) {
							resul[0] = new Integer(MEDIO_EFECTIVO);  // O CUASI MONEDAS (tikets)
							resul[1] = null;
						}else{
							if (formaPago == FormaPago.OTROS) {
								resul[0] = new Integer(MEDIO_OTROS);
								resul[1] = null;
							}
						}
						break;
					}
				} else { // HABER
					switch (cuenta.getTipoFondos().intValue()) {
					case CARTERA:
						/* En este caso solo se puede buscar cheques de terceros en cartera*/
						if (formaPago == FormaPago.CHEQUE) {//Cheque
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(false);
						}
						break;
					case BANCOS:
						/* En este caso se emitiran cheques o se creara una acreditacion o importe directo*/
						if (formaPago == FormaPago.ACREDITACION_BANCARIA) {//Acrditacion
							resul[0] = new Integer(MEDIO_ACREDITACION); 
							resul[1] = new Boolean(true);
						}else {
							if (formaPago == FormaPago.CHEQUE) {//Cheque
								resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
								resul[1] = new Boolean(true);
							}else {
								if (formaPago == FormaPago.DEPOSITO_BANCARIO || formaPago == FormaPago.TARJETA_DEBITO){
									resul[0] = new Integer(MEDIO_DEPOSITO); 
									resul[1] = null;
								}
							}
						}
						break;
					case OTROS:
						/* En este caso solo se puede hacer un importe directo*/
						if (formaPago == FormaPago.EFECTIVO || formaPago == FormaPago.TICKETS || formaPago == FormaPago.TARJETA_DEBITO) {
							resul[0] = new Integer(MEDIO_EFECTIVO);  // O CUASI MONEDAS (tikets)
							resul[1] = null;
						}else{
							if (formaPago == FormaPago.OTROS) {
								resul[0] = new Integer(MEDIO_OTROS);
								resul[1] = null;
							}
						}
						break;
					}
				}
				
				break;
			case RECHAZOS_DE_CHEQUES_PROPIOS:
				if (signo) { // DEBE
//		    		como cuenta unica al (debe) solo 6 importe // Esto se controla en la configuracion del concepto
					switch (cuenta.getTipoFondos().intValue()) {
					case BANCOS:
						if (formaPago == FormaPago.DEPOSITO_BANCARIO) { // cambiar a cuenta banco
							resul[0] = new Integer(MEDIO_DEPOSITO); 
							resul[1] = null;
						}
						break;
					}
				} else { // HABER
					switch (cuenta.getTipoFondos().intValue()) {
					case OTROS:
						resul[0] = null; 
						resul[1] = null;
						break;
					case BANCOS:
//						2 y 3 solo busqueda
						if (formaPago == FormaPago.CHEQUE) {//Cheque
							resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
							resul[1] = new Boolean(false);	
						}else {
							if (formaPago == FormaPago.ACREDITACION_BANCARIA) {
								resul[0] = new Integer(MEDIO_ACREDITACION); 
								resul[1] = new Boolean(false);	
							}
						}
						break;
					case CARTERA:
						resul[0] = null; 
						resul[1] = null;			
						break;
					}
				}
				break;
			case RECHAZOS_DE_CHEQUES_TERCEROS:
				if (signo) { // DEBE
					switch (cuenta.getTipoFondos().intValue()) {
					case OTROS:
						/* En este caso solo se buscan cheques de terceros*/
						resul[0] = null; 
						resul[1] = null;		
						break;
					case CARTERA:
//						2 busqueda cheque tercero
						if (formaPago == FormaPago.CHEQUE){
							resul[0] = new Integer(MEDIO_CHEQUE_TERCERO); 
							resul[1] = new Boolean(false);
						}		
						break;
					case BANCOS:
						/* En este caso solo se puede buscar cheques de terceros en cartera*/
						resul[0] = null; 
						resul[1] = null;		
						break;
					}
				} else { // HABER
					switch (cuenta.getTipoFondos().intValue()) {
					case BANCOS:
						if (formaPago == FormaPago.DEPOSITO_BANCARIO) { // cambiar a cuenta banco
							resul[0] = new Integer(MEDIO_DEPOSITO); 
							resul[1] = null;
						}
						break;
					}
				}
//		    		la cuenta unica va al haber tipo banco y solo medio 6
				break;
//				case OTROS_MOVIMIENTOS: 
//					switch (cuenta.getTipoFondos().intValue()) {
//					case OTROS:
//						/* En este caso solo se pueden hacer importes directos */
//						resul[0] = new Integer(MEDIO_EFECTIVO); 
//						resul[1] = null;
//						break;
//					case CARTERA:
//						/* No se puede usar en esta clase */
//						resul[0] = null; 
//						resul[1] = null;			
//						break;
//					case BANCOS:
//						/* No se puede usar en esta clase */
//						resul[0] = null; 
//						resul[1] = null;
//						break;
//					}
//					break;
			case TRANSF_CHEQUES_DIFERIDOS_BANCO:
				if (signo) { // DEBE
					
				} else { // HABER
					
				}
//		    		Nada , solo la usa el sistema
//		    		switch (cuenta.getTipoFondos().intValue()) {
//		    		case BANCOS:
//		    			/* Solo se puede buscar cheques propios */
//		    			resul[0] = new Integer(MEDIO_CHEQUE_PROPIO); 
//		    			resul[1] = new Boolean(false);
//		    			break;
//		    		case OTROS:
//		    			/* No se puede usar en esta clase */
//		    			resul[0] = null; 
//						resul[1] = null;			
//		    			break;
//					case CARTERA:
//						/* No se puede usar en esta clase */
//						resul[0] = null; 
//						resul[1] = null;			
//						break;
//					}
				break;
			}
		}
    	return resul;
    }
    
    public List cuentasUnicas(List cuentas){
    	List permitidas = new ArrayList();
    	List mediosPosibles = new ArrayList();
    	signoCuentaUnica = 0;
    	int suma = 0;
    	switch (clase) {
    	case COBROS:
    		signoCuentaUnica = ACREDITA;
    		suma = 0; // para las no de fondos 
    		break;
    	case PAGOS:
    		signoCuentaUnica = DEBITA;
    		suma = 0; // para las no de fondos
    		break;
    	case DEPOSITOS:
    		signoCuentaUnica = DEBITA;
    		suma = BANCOS; // AGREGAR, medio de pago 6
    		mediosPosibles.add(new Long(6));
    		break;
    	case OTROS_MOV_BANCOS_Y_CARTERAS: //Renombrar OTROS_MOVIMIENTOS DE FONDOS 
    		signoCuentaUnica = 0;
    		suma = OTROS + BANCOS + CARTERA; 
    		break;
    	case RECHAZOS_DE_CHEQUES_PROPIOS: //Renombrar RECHAZOS_DE_CHEQUES Y TRANSFERENCIAS _PROPIOS  
    		signoCuentaUnica = DEBITA;
    		suma = BANCOS; // AGREGAR, medio de pago 2 y 6
    		mediosPosibles.add(new Long(2));
    		mediosPosibles.add(new Long(6));
    		break;
    	case RECHAZOS_DE_CHEQUES_TERCEROS:
    		signoCuentaUnica = ACREDITA;
    		suma = BANCOS; // AGREGAR, medio de pago 2 y 6
    		mediosPosibles.add(new Long(2));
    		mediosPosibles.add(new Long(6));
    		break;
//    	case OTROS_MOVIMIENTOS: 
//    		signoCuentaUnica = 0;
//    		suma = OTROS;
//    		break;
    	case TRANSF_CHEQUES_DIFERIDOS_BANCO: //Renombrar  CAMBIO DE ESTADOS DE CHEQUE Y TEF   Y no permitir carga manual
    		signoCuentaUnica = DEBITA;
    		suma = BANCOS;
    		break;
    	}
    	if (suma != 0) { // para las cuentas que no son de fondos fijarse si es 0 la suma 
    		Iterator iter = cuentas.iterator();
    		while (iter.hasNext()) {
    			PlanCuentaDos cuenta = (PlanCuentaDos) iter.next();
    			if(cuenta.getTipoFondos() != null && (suma & cuenta.getTipoFondos().intValue()) > 0) {
    				if (mediosPosibles.isEmpty()) {// Si esta vacia acepta cualquier medio
    					permitidas.add(cuenta);
					}else { // Sino solo los que esten contenidos en la lista
						if (mediosPosibles.contains(cuenta.getFormaPago().getIdFormaPago())) {
							permitidas.add(cuenta);
						}
					}
    			}
    		}
		}else{
			// VERIFICAR SI ESTO ES HASI O SOLO SE PONEN LAS QUE NO SON DE FONDOS
			permitidas = cuentas; 
		}
    	return permitidas;
    }
    
    public List cuentasCompartidas(List cuentas){
    	List permitidas = new ArrayList();
    	List mediosPosibles = new ArrayList();
    	int suma = 0;
    	switch (clase) {
    	case COBROS:
    		suma = OTROS + BANCOS + CARTERA;
    		break;
    	case PAGOS:
    		suma = OTROS + BANCOS + CARTERA;
    		break;
    	case DEPOSITOS:
    		suma = OTROS + CARTERA;
    		break;
    	case OTROS_MOV_BANCOS_Y_CARTERAS:
    		suma = OTROS + BANCOS + CARTERA;
    		break;
    	case RECHAZOS_DE_CHEQUES_PROPIOS:
    		suma = BANCOS; // AGREGAR, medio de pago 2, 3  y 6
    		mediosPosibles.add(new Long(2));
    		mediosPosibles.add(new Long(3));
    		mediosPosibles.add(new Long(6));
    		break;
    	case RECHAZOS_DE_CHEQUES_TERCEROS:
    		suma = CARTERA;
    		break;
//    	case OTROS_MOVIMIENTOS:
//    		suma = OTROS;
//    		break;
    	case TRANSF_CHEQUES_DIFERIDOS_BANCO:
    		suma = BANCOS;
    		break;
    	}
    	if (suma != 0) {
    		Iterator iter = cuentas.iterator();
    		while (iter.hasNext()) {
    			PlanCuentaDos cuenta = (PlanCuentaDos) iter.next();
    			if(cuenta.getTipoFondos() != null && (suma & cuenta.getTipoFondos().intValue()) > 0){
    				if (mediosPosibles.isEmpty()) {// Si esta vacia acepta cualquier medio
    					permitidas.add(cuenta);
    				}else { // Sino solo los que esten contenidos en la lista
    					if (mediosPosibles.contains(cuenta.getFormaPago().getIdFormaPago())) {
    						permitidas.add(cuenta);
    					}
    				}
    			}
    		}
		} // VERIFICAR SI ES EL MISMO CASO DE LAS CUENTAS UNICAS
    	return permitidas;
    }
    
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ClaseFondo) {
			ClaseFondo aux = (ClaseFondo)obj;
//			if(aux.getId().equals(idCaja)) {
//				result = true;
//			}
		}
		return result;
	}
	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}
}

