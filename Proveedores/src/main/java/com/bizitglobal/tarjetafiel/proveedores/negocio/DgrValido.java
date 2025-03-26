package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.proveedores.exception.DgrNoValidoException;

public class DgrValido {
	private String dgr = null;
	
	/**
	 * Se construye con un nro de dgr y segun su jurisdiccion lo valida
	 * @param numeroDgr
	 * @param jurisdiccion
	 * @throws DgrNoValidoException
	 */
	public DgrValido(String numeroDgr, int jurisdiccion) throws DgrNoValidoException {
		switch (jurisdiccion) {
		case 1: // Para San Juan
			dgrSanJuan(numeroDgr);
			break;
		case 2: // Para Cordoba
			
			break;
		case 3: // Para Buenos Aires
			
			break;
		default:
			break;
		}
		
		System.out.println("Construccion OK!!!");
	}

	private void dgrSanJuan(String numeroDgr) throws DgrNoValidoException {
		int digito = 0;
		
		if(numeroDgr.length()==10) {
			String tipoNumero      = numeroDgr.substring(0,3);
			String numero  = numeroDgr.substring(3,9);
			String verificador = numeroDgr.substring(9,10);
			
			System.out.println("Tipo ="+tipoNumero);
			System.out.println("Numero ="+numero);
			System.out.println("Verificador ="+verificador);
			
			try {
				if(tipoNumero.equals("000")) {
					digito = verificarUno(numero);
				} else {
					digito = verificarDos(numero);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DgrNoValidoException("El número de inscripción dgr no es valido.");
			}
			
			if(digito != new Integer(verificador).intValue()) {
				throw new DgrNoValidoException("El número de inscripción dgr no es valido.");
			}
			
		} else {
			throw new DgrNoValidoException("El número de inscripción dgr no es valido.");
		}
		
		dgr = numeroDgr;
	}
	
	private int verificarUno(String num) {
		int result = 0;
		String temp = num;
		int suma = 0;
		
		for(int i=0;i<num.length();i++) {
			String n1 = temp.substring(i,(i+1));
			int n2 = new Integer(n1).intValue() * (i+1);
			String n2Temp = completarADos(n2+"");
			int n3 = new Integer(n2Temp.substring(1,2)).intValue();
			suma += n3;
		}
		
		String sumaTemp = completarADos(suma+"");
		int digito = new Integer(sumaTemp.substring(1,2)).intValue();
		if(digito!=0) {
			result = 10-digito;
		}
		
		return result;
	}
	
	private int verificarDos(String num) {
		int result = 0;
		String fijo = "139713";
		int suma = 0;
		
		for(int i=0;i<num.length();i++) {
			suma += (new Integer(num.substring(i,(i+1))).intValue() * new Integer(fijo.substring(i,(i+1))).intValue());
		}

		int resto = suma % 11;

		if((11-resto)<10) {
			result = 11-resto;
		} else {
			result = 11-resto-10;
		}

		return result;
	}	
	
	private String completarADos(String completar) {
		String result = completar;
		
		if(completar.length()==0) {
			result = "00";
		} else if(completar.length()==1) {
			result = completar.substring(0,1)+"0";
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		try {
			new DgrValido("9011707350",1);
			new DgrValido("9011707558",1);
			new DgrValido("9011708611",1);
			new DgrValido("9011709099",1);
			new DgrValido("9011709516",1);
			new DgrValido("9011710911",1);
			new DgrValido("9011711275",1);
		} catch (DgrNoValidoException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getDgr() {
		return dgr;
	}

}
