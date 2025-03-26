package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.CajaArqueo;
	import com.tarjetafiel.caja.vo.CajaCierre;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	
	[Bindable]
	public class ArqueoCajaModel extends EventDispatcher
	{
		
		private var _formaPagoValoresList:ArrayCollection = new ArrayCollection();			
		
		public static const ARQUEO_DEFINITIVO:String = "z";
		
		public static const ARQUEO_PROVISORIO:String = "x";
		
		private var _tipoDeArqueo:String;			
		
		public var cajaCierreList:ArrayCollection = new ArrayCollection();
		
		public var mostrarDiferencia:Boolean = false;
			
		public function ArqueoCajaModel()
		{
		}
		
		public function set formaPagoValoresList(list:ArrayCollection):void{						
			
			_formaPagoValoresList = list;
			
			ordenarFormaPagoValores();
			
			dispatchEvent(new Event("formaPagoValoresListChanged"));
			
		}  
		
		[Bindable (event="formaPagoValoresListChanged")]
		public function get formaPagoValoresList():ArrayCollection{
			return _formaPagoValoresList;
		}
		
		private function ordenarFormaPagoValores():void{
			var sort:Sort = new Sort();						
			sort.fields = [new SortField("multiplo"), new SortField("formaPago")];
			_formaPagoValoresList.sort = sort;
			_formaPagoValoresList.refresh(); 			 
		}
		
				
		public function set tipoDeArqueo(tipo:String):void{			
			_tipoDeArqueo = tipo;	
			for each(var cierre:CajaCierre in cajaCierreList){
				cierre.tipo = _tipoDeArqueo;
			}					
			dispatchEvent(new Event("changedTipoArqueo"));
		}
		
		[Bindable (event="changedTipoArqueo")]
		public function get tipoDeArqueo():String{
			return _tipoDeArqueo;
		}
		
		public function addCajaCierre(cierre:CajaCierre):void{								
			cierre.fecha = new Date();			
			this.cajaCierreList.addItem(cierre);
		}		
		
		public function changedMonto(cajaCierre:CajaCierre,arqueo:CajaArqueo,monto:Number,cantidad:int):void{			
			cajaCierre.totalArqueo -= arqueo.monto;
			arqueo.monto = monto;
			arqueo.cantidad = cantidad;
			cajaCierre.totalArqueo += arqueo.monto;
		}
		
		public function completeArqueoProvisorio(cierres:Array):void{
			mostrarDiferencia = true;
			for each(var cierre:CajaCierre in cierres){
				for each(var cierreActual:CajaCierre in cajaCierreList){
					if(cierre.caja.idCajaMP == cierreActual.caja.idCajaMP){						
						cierreActual.totalContable = cierre.totalContable;
						cierreActual.diferencia = cierre.diferencia;
					}
					
				}
			}
		}
		

	}
}