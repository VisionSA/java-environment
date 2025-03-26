package com.tarjetafiel.caja.vo.util
{
	public interface Comparable
	{
		/*
		 *• un número negativo si el objeto receptor es menor que el objeto recibido como argumento
		 *• un número positivo si el objeto receptor es mayor que el objeto recibido como argumento, o
		 *• cero si ambos son iguales. 
		 */
		function compararObjeto(obj:Object):int;
	}
}