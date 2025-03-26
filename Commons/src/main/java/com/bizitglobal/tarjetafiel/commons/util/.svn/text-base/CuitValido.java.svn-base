package com.bizitglobal.tarjetafiel.commons.util;

import com.bizitglobal.tarjetafiel.commons.exception.CuitNoValidoException;


public class CuitValido {
	private Long cuit;
	private int[] matriz = {5,4,3,2,7,6,5,4,3,2};
	
	public CuitValido(String unCuit) throws CuitNoValidoException {
		int v1 = 0;
		int v2 = 0;
		int v3 = 0;
			int verificador = new Integer(unCuit.substring(10, 11)).intValue();
			
			for(int i=0;i<10;i++) {
				int numero = new Integer(unCuit.substring(i, (i+1))).intValue();
				v1 += matriz[i]*numero;
			}
					
			v2 = v1 % 11;
			v3 = 11 - v2;
					
//			if(!((v3==11) && (verificador==0))) {
//				if(!((v3==10) && (verificador==9))) {
//					if(v3!=verificador) {
//						throw new CuitNoValidoException("El cuit no es válido.");
//					}
//				}
//			}
			
			if (v3 == 11) {
				v3 = 0;
			}
			
			if(v3!=verificador) {
				throw new CuitNoValidoException("El cuit no es valido.");
			}
				
			cuit = new Long(unCuit);
		
	}

	public Long getCuit() {
		return cuit;
	}

	public String getIdentificador() {
		return cuit.toString().substring(0,2);
	}
	
	public String getDni() {
		return cuit.toString().substring(2,10);
	}

	public String getVerificador() {
		return cuit.toString().substring(10,11);
	}
	
	public static void main(String[] args) {
		try {
			CuitValido cuit1  = new CuitValido("23274094699");
			CuitValido cuit2  = new CuitValido("23209999153");
			CuitValido cuit3  = new CuitValido("23217855969");
			CuitValido cuit4  = new CuitValido("23224742754");
			CuitValido cuit5  = new CuitValido("23228218669");
			CuitValido cuit6  = new CuitValido("23236820254");
			CuitValido cuit7  = new CuitValido("20015880385");			
			CuitValido cuit8  = new CuitValido("23251395349");
			CuitValido cuit9  = new CuitValido("20017391322");
			CuitValido cuit10 = new CuitValido("23106020094");
			
			System.out.println("Cuit 1 ->"+"Dni:"+cuit1.getDni()+"Ver:"+cuit1.getVerificador()+
								"Id:"+cuit1.getIdentificador());
			
			System.out.println("Cuit 2 ->"+"Dni:"+cuit2.getDni()+"Ver:"+cuit2.getVerificador()+
					"Id:"+cuit2.getIdentificador());
			
			System.out.println("Cuit 3 ->"+"Dni:"+cuit3.getDni()+"Ver:"+cuit3.getVerificador()+
					"Id:"+cuit3.getIdentificador());
			
			System.out.println("Cuit 4 ->"+"Dni:"+cuit4.getDni()+"Ver:"+cuit4.getVerificador()+
					"Id:"+cuit4.getIdentificador());
			
			System.out.println("Cuit 5 ->"+"Dni:"+cuit5.getDni()+"Ver:"+cuit5.getVerificador()+
					"Id:"+cuit5.getIdentificador());
			
			System.out.println("Cuit 6 ->"+"Dni:"+cuit6.getDni()+"Ver:"+cuit6.getVerificador()+
					"Id:"+cuit6.getIdentificador());
			
			System.out.println("Cuit 7 ->"+"Dni:"+cuit7.getDni()+"Ver:"+cuit7.getVerificador()+
					"Id:"+cuit7.getIdentificador());
			
			System.out.println("Cuit 8 ->"+"Dni:"+cuit8.getDni()+"Ver:"+cuit8.getVerificador()+
					"Id:"+cuit8.getIdentificador());
			
			System.out.println("Cuit 9 ->"+"Dni:"+cuit9.getDni()+"Ver:"+cuit9.getVerificador()+
					"Id:"+cuit9.getIdentificador());
			
			System.out.println("Cuit 10 ->"+"Dni:"+cuit10.getDni()+"Ver:"+cuit10.getVerificador()+
					"Id:"+cuit10.getIdentificador());
			
		} catch (CuitNoValidoException e) {
			e.printStackTrace();
		}
	}
}
