package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.LiqComercioLP;
	
	import mx.collections.ArrayCollection;
	
	public class LiqComercioModel
	{
		[Bindable]public var arraySucursalesSeleccionadas:ArrayCollection = new ArrayCollection();
		
		[Bindable]public var arrayLIquidaciones:ArrayCollection = new ArrayCollection();
		
		[Bindable]public var liqComercioLPSelect:LiqComercioLP;		
		
		[Bindable]public var arrayCtaCteComercio:ArrayCollection = new ArrayCollection();	
		
		[Bindable]public var totalNetoLiquidado:Number;
		[Bindable]public var totalBrutoLiquidado:Number;					
		[Bindable]public var totalPagarLiquidado:Number;
					
		public function LiqComercioModel()
		{
						
		}			

	}
}