package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import mx.collections.ArrayCollection;
	
	public class EmpresaModel
	{
		[Bindable]public var empresa:Empresa;
		
		[Bindable]public var filtro:Filtro;
		
		[Bindable]public var paginador:Paginador;
		
		[Bindable]public var empresasList:ArrayCollection = new ArrayCollection();
		
		[Bindable]public var labelButtonBusqueda:String = "Buscar";
		
		[Bindable]public var labelEmpresaSeleccionada:String = "No hay ninguna empresa selccionada";			

 		public static const LIQUIDACION_POR_CUIT:Number = 1;
 		
    	public static const LIQUIDACION_POR_COMERCIO:Number = 2;
		
		public function EmpresaModel()
		{
		}
		
		public function getSucursales():void{
			
		}

	}
}