package com.tarjetafiel.caja.vo
{
	import com.tarjetafiel.caja.vo.util.Comparable;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.general.negocio.Partido")]
	public class Partido implements Comparable
	{
		public var idPartido:Number;
		public var descripcion:String;
		public var idCobrador:Number;
		public var idAbogado:Number;
		public var esLejano:String;
		public var provincia:Provincia;
		public var informeAmbiental:String;
		public var cobrador : Cobrador;
		public var abogado : Abogado; /* Atributos nombre, apellido, idColaborador */


	    public function Partido() {
	    	
	    }
		
		public function compararObjeto(obj:Object):int {
			if (idPartido < (obj as Partido).idPartido) return -1;
			if (idPartido > (obj as Partido).idPartido) return 1;
			return 0;
		}
		
	}
	
}