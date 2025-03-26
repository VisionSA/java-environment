package com.bizitglobal.tarjetafiel.commons.filtros;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;

public class Filtro {
	private Vector campos;
	private Vector valores;
	private Vector operadores;
	private Vector join;
	private String funcion = "";
	private Vector orderBy;
	private String consultaAMano;
	private static Calendar fecha = Calendar.getInstance();
	
	public final static int IGUAL = 1;
	public final static int LIKE = 2;
	public final static int MAYOR = 3;
	public final static int MENOR = 4;
	public final static int MAYOR_IGUAL = 5;
	public final static int MENOR_IGUAL = 6;
	public final static int DISTINTO = 7;
	public final static int LIKEXS = 8;
	public final static int NOTLIKE = 9;
	public final static int IN = 10;
	public final static int NOTIN = 11;
	public final static int NOTNULL = 12;
	public final static int NULL = 13;
	public final static int LIKE_IZQ = 14;
	public final static int LIKE_DER = 15;
	public final static int IGUAL_NUMERO = 16;
	
	private final static String LEFT_JOIN_FETCH = " left join fetch ";
	
	//private final static String LEFT_JOIN_FETCH = " left join  ";
	
	public static final int IGUAL_FECHA = 17;
	
	public static final int IGUAL_MANO = 18;
	
	public Filtro() {
		this(new Vector(), new Vector(), new Vector(), new Vector(), new Vector());
	}
	
	public Filtro(Vector campos, Vector valores, Vector operadores, Vector join, Vector orderBy) {
		super();
		this.campos = campos;
		this.valores = valores;
		this.operadores = operadores;
		this.join = join;
		this.orderBy = orderBy;
	}
	
	/**
	 * @deprecated
	 * @param campos
	 * @param valores
	 */
	public Filtro(Vector campos, Vector valores) {
		super();
		this.campos = campos;
		this.valores = valores;
		this.operadores = new Vector();
	}
	
	/**
	 * @deprecated
	 * @param unCampo
	 * @param unValor
	 */
	public Filtro(String unCampo, Object unValor) {
		this();
		agregarCampoValor(unCampo, unValor);
	}
	
	public Filtro(String unCampo, int unOperador, Object unValor) {
		this();
		agregarCampoOperValor(unCampo, unOperador, unValor);
	}
	
	/**
	 * @deprecated
	 * @param unCampo
	 * @param unValor
	 * @param unaFuncion
	 */
	public Filtro(String unCampo, Object unValor, String unaFuncion) {
		this();
		agregarCampoValor(unCampo, unValor);
		agregarfuncion(unaFuncion);
	}

	public Vector getCampos() {
		return campos;
	}
	
	public void setCampos(Vector campos) {
		this.campos = campos;
	}
	
	public Vector getValores() {
		return valores;
	}
	
	public void setValores(Vector valores) {
		this.valores = valores;
	}
	
	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Vector getOperadores() {
		return operadores;
	}

	public void setOperadores(Vector operadores) {
		this.operadores = operadores;
	}

	public void agregarCampo(String unCampo) {
		campos.add(unCampo);
	}
	
	public void agregarValor(Object unValor) {
		valores.add(unValor);
	}
	
	public void agregarOperador(int unOperador) {
		operadores.add(new Integer(unOperador));
	}
	
	public void agregarJoin(String campo) {
		join.add(campo);
	}
	
	public void agregarOrderBy(String campo) {
		orderBy.add(campo);
	}
	
	/**
	 * @deprecated
	 * @param unCampo
	 * @param unValor
	 */
	public void agregarCampoValor(String unCampo, Object unValor) {
		this.agregarCampo(unCampo);
		this.agregarValor(unValor);
	}
	
	public void agregarCampoOperValor(String unCampo, int unOperador, Object unValor) {
		this.agregarCampo(unCampo);
		this.agregarOperador(unOperador);
		this.agregarValor(unValor);
	}
	 
	/**
	 * Estan permitidos unicamente para unOperador NOTNULL O NULL
	 * */
	public void agregarCampoComparacionNulo(String unCampo, int unOperador) {
		this.agregarCampo(unCampo);
		this.agregarOperador(unOperador);
		this.agregarValor(" ");
	}
	
	public void agregarfuncion(String unaFuncion) {
		funcion += unaFuncion;
	}
	
	public void reset() {
		campos = new Vector();
		valores = new Vector();
		operadores = new Vector();
		join = new Vector();
		orderBy = new Vector();
		funcion = "";
	}
	
	public static String getTO_DATE(Calendar fecha) {
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int anio = fecha.get(Calendar.YEAR);
		
		return getTO_DATE(dia, mes, anio);
	}

	public static String getTO_DATE(int dia, int mes, int anio) {
		return "TO_DATE('" +dia+ "/" +mes+ "/" +anio+ "','DD/MM/YYYY')";
	}
	
	public static String getTO_DATE(Timestamp timestamp) {
		fecha = Convertidores.timestampToCalendar(timestamp);
		return getTO_DATE(fecha);
	}

	public static String getTO_DATE(Date date) {
		fecha.setTime(date); 
		return getTO_DATE(fecha);
	}
	
	public String getHQL() {
		StringBuffer result = new StringBuffer("");
		String obj = "obj";
		
		// Si hay algo que agregar para los join, lo agregamos.
		for(int i=0;i<join.size();i++) {
			result.append(Filtro.LEFT_JOIN_FETCH + obj + "." + join.get(i) +" ");
			
		}

		if((campos.size() == valores.size()) && (valores.size() > 0)) {
			result.append("WHERE ");
			if(valores.size() == operadores.size()) {
				for(int i=0;i<valores.size();i++) {
					Integer oper = (Integer)operadores.elementAt(i);
					switch (oper.intValue()) {					
					case IGUAL:
						result.append(obj+"."+campos.elementAt(i)+" = "+valores.elementAt(i));
						break;
					case LIKE:
						result.append("upper("+obj+"."+campos.elementAt(i)+")"+" LIKE '%"+valores.elementAt(i).toString().toUpperCase()+"%'");
						break;
					case MAYOR:
						result.append(obj+"."+campos.elementAt(i)+" > "+valores.elementAt(i));
						break;
					case MENOR:
						result.append(obj+"."+campos.elementAt(i)+" < "+valores.elementAt(i));
						break;
					case MAYOR_IGUAL:
						result.append(obj+"."+campos.elementAt(i)+" >= "+valores.elementAt(i));
						break;
					case MENOR_IGUAL:
						result.append(obj+"."+campos.elementAt(i)+" <= "+valores.elementAt(i));
						break;
					case DISTINTO:
						result.append(obj+"."+campos.elementAt(i)+" <> "+valores.elementAt(i));
						break;
					case LIKEXS:
						result.append("upper("+obj+"."+campos.elementAt(i)+")"+" LIKE '"+valores.elementAt(i).toString().toUpperCase()+"'");
						break;
					case LIKE_DER:
						result.append("upper("+obj+"."+campos.elementAt(i)+")"+" LIKE '"+valores.elementAt(i).toString().toUpperCase()+"%'");
						break;
					case LIKE_IZQ:
						result.append("upper("+obj+"."+campos.elementAt(i)+")"+" LIKE '%"+valores.elementAt(i).toString().toUpperCase()+"'");
						break;
					case NOTLIKE:
						result.append("upper("+obj+"."+campos.elementAt(i)+")"+" NOT LIKE '"+valores.elementAt(i).toString().toUpperCase()+"'");
						break;
					case IN:
						result.append(obj+"."+campos.elementAt(i)+" IN ("+valores.elementAt(i)+")");
						break;
					case NOTIN:
						result.append(obj+"."+campos.elementAt(i)+" NOT IN ("+valores.elementAt(i)+")");
						break;
					case NOTNULL:
						result.append(obj+"."+campos.elementAt(i)+" IS NOT NULL "+ valores.elementAt(i));
						break;
					case NULL:
						result.append(obj+"."+campos.elementAt(i)+" IS NULL "+ valores.elementAt(i));
						break;
					case IGUAL_NUMERO:
						result.append("to_number("+obj+"."+campos.elementAt(i)+") = "+valores.elementAt(i));
						break;					
					case IGUAL_FECHA:
						result.append("to_date("+obj+"."+campos.elementAt(i)+") = "+valores.elementAt(i));
						break;
					case IGUAL_MANO:
						result.append(campos.elementAt(i)+" = "+valores.elementAt(i));
						break;
					default:
						result.append(" ");
						break;
					}
					if(((i+1) <= (valores.size()-1)) && (valores.elementAt(i+1)!="") && (valores.elementAt(i+1)!=null)) {
						result.append(" AND ");
					}
				}
			}else {
				for(int i=0;i<valores.size();i++) {
					if((valores.elementAt(i) != "") && (valores.elementAt(i) != null)) { 
						if(valores.elementAt(i) instanceof String) {
							result.append("upper("+obj+"."+campos.elementAt(i)+")"+" LIKE "+"'%"+valores.elementAt(i).toString().toUpperCase()+"%'");
						} else {
							result.append(obj+"."+campos.elementAt(i)+" = "+valores.elementAt(i));
						}
		
						if(((i+1) <= (valores.size()-1)) && (valores.elementAt(i+1)!="") && (valores.elementAt(i+1)!=null)) {
							result.append(" AND ");
						}
					}
				}
			}
		}
		
		result.append(" " + funcion);
		// ULTIMO AGREGADO EN ESTE METODO. PUEDE PROVOCAR FALLAS EN LUGARES DONDE SE ALLA USADO MAL.
		if (!orderBy.isEmpty()) {
			result.append(" ORDER BY ");
			for(int i=0;i<orderBy.size();i++) {
				result.append(obj+"."+orderBy.get(i));
				if (i < orderBy.size() - 1) {
					result.append(" , ");
				}
			}
		}
		return result.toString();
	}
	
	public void setConsultaAMano(String consultaAMano) {
		this.consultaAMano = consultaAMano;
	}
	
	public String getConsultaAMano() {
		return consultaAMano;
	}
	
	public String getSQL() {
		StringBuffer result = new StringBuffer("");
				
		// Si hay algo que agregar para los join, lo agregamos.
//		for(int i=0;i<join.size();i++) {
//			result.append(Filtro.LEFT_JOIN_FETCH + obj + "." + join.get(i) +" ");
//		}

		if((campos.size() == valores.size()) && (valores.size() > 0)) {
			result.append(" WHERE ");
			if(valores.size() == operadores.size()) {
				for(int i=0;i<valores.size();i++) {
					Integer oper = (Integer)operadores.elementAt(i);
					switch (oper.intValue()) {					
					case IGUAL:
						result.append(campos.elementAt(i)+" = "+valores.elementAt(i));
						break;
					case LIKE:
						result.append("UPPER("+campos.elementAt(i)+") LIKE '%"+valores.elementAt(i).toString().toUpperCase()+"%'");
						break;
					case MAYOR:
						result.append(campos.elementAt(i)+" > "+valores.elementAt(i));
						break;
					case MENOR:
						result.append(campos.elementAt(i)+" < "+valores.elementAt(i));
						break;
					case MAYOR_IGUAL:
						result.append(campos.elementAt(i)+" >= "+valores.elementAt(i));
						break;
					case MENOR_IGUAL:
						result.append(campos.elementAt(i)+" <= "+valores.elementAt(i));
						break;
					case DISTINTO:
						result.append(campos.elementAt(i)+" <> "+valores.elementAt(i));
						break;
					case LIKEXS:
						result.append("UPPER("+campos.elementAt(i)+") LIKE '"+valores.elementAt(i).toString().toUpperCase()+"'");
						break;
					case NOTLIKE:
						result.append("UPPER("+campos.elementAt(i)+") NOT LIKE '"+valores.elementAt(i).toString().toUpperCase()+"'");
						break;
					case IN:
						result.append(campos.elementAt(i)+" IN ("+valores.elementAt(i)+")");
						break;
					case NOTIN:
						result.append(campos.elementAt(i)+" NOT IN ("+valores.elementAt(i)+")");
						break;
					case NOTNULL:
						result.append(campos.elementAt(i)+" IS NOT NULL "+ valores.elementAt(i));
						break;
					case NULL:
						result.append(campos.elementAt(i)+" IS NULL "+ valores.elementAt(i));
						break;
					case IGUAL_NUMERO:
						result.append("to_number("+campos.elementAt(i)+") = "+valores.elementAt(i));
						break;
					case IGUAL_FECHA:
						result.append("to_date("+campos.elementAt(i)+") = "+valores.elementAt(i));
						break;	
					
					default:
						result.append(" ");
						break;
					}
					if(((i+1) <= (valores.size()-1)) && (valores.elementAt(i+1)!="") && (valores.elementAt(i+1)!=null)) {
						result.append(" AND ");
					}
				}
			}else {
				for(int i=0;i<valores.size();i++) {
					if((valores.elementAt(i) != "") && (valores.elementAt(i) != null)) { 
						if(valores.elementAt(i) instanceof String) {
							result.append("UPPER("+campos.elementAt(i)+") LIKE '%"+valores.elementAt(i).toString().toUpperCase()+"%'");
						} else {
							result.append(campos.elementAt(i)+" = "+valores.elementAt(i));
						}
		
						if(((i+1) <= (valores.size()-1)) && (valores.elementAt(i+1)!="") && (valores.elementAt(i+1)!=null)) {
							result.append(" AND ");
						}
					}
				}
			}
		}
		
		result.append(" " + funcion);
		
		if (!orderBy.isEmpty()) {
			result.append(" ORDER BY ");
			for(int i=0;i<orderBy.size();i++) {
				result.append(orderBy.get(i));
				if (i < orderBy.size() - 1) {
					result.append(" , ");
				}
			}
		}

		return result.toString();
	}
	
	public boolean estaVacio() {
		return (campos.size()==0) && (valores.size()==0) && (operadores.size()==0);
	}
	public String toString(){
	
		String ret = "**********************************FILTRO**********************************\n";
		ret += "---------------CAMPOS---------------\n";
		for(int i =0; i< campos.size() ; i++){
			ret += "*Campo nro "+i+campos.get(i)+"\n";
		}
		ret += "---------------VALORES---------------\n";
		for(int i =0; i< valores.size() ; i++){
			ret += "*valor nro "+i+valores.get(i)+"\n";
		}
		ret += "---------------OPERADORES---------------\n";
		for(int i =0; i< operadores.size() ; i++){
			ret += "*operador nro "+i+operadores.get(i)+"\n";
		}
		ret += "---------------JOIN---------------\n";
		for(int i =0; i< join.size() ; i++){
			ret += "*join nro "+i+join.get(i)+"\n";
		}
		ret += "---------------FUNCION---------------\n";
		ret += "*funcion:"+funcion+"\n";
		ret += "---------------ORDERBY---------------\n";
		for(int i =0; i< orderBy.size() ; i++){
			ret += "*orderBy nro "+i+orderBy.get(i)+"\n";
		}
		ret += "---------------CONSULTA A MANO---------------\n";
		ret += "*consultaAMano:"+consultaAMano+"\n";
		return ret;
	}

	public Vector getJoin() {
		return join;
	}

	public void setJoin(Vector join) {
		this.join = join;
	}
	

}
