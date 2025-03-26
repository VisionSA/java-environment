package views.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Cuota;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	import com.tarjetafiel.caja.vo.ListaPrecioItem;
	
	import events.SimuladorEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.utils.ObjectUtil;
	
	[Bindable]
	public class SimuladorViewModel extends EventDispatcher
	{
		public var dispatcher:IEventDispatcher;
		
		private var _cliente:ClienteTransaccion;
		
		private var _arrayCuotas:ArrayCollection;
		
		private  var _listaPrecioSelected:ListaPrecio;
		
		private var _cuotaSeleccionada:Cuota; 
		
		private var _monto : Number =0 ; 
		
		private var _disponible : Object;
		
		public var habilitarSimular : Boolean=false;
		
		public var cantidadCuotaSeleccionada : Number = 0;
		 
		public var isCuotaSeleccionada:Boolean=false;
		
		public var importeCuota:Number = 0;
		
		public var cuotasDisponibles:String = ""; 
		
				
		public function SimuladorViewModel()
		{
			
		}
		
		public function calcularCuota(monto:Number):void{
			var evt:SimuladorEvent = new SimuladorEvent(SimuladorEvent.CALCULAR_CUOTAS);
			evt.monto = monto;
			evt.listaPrecio =_listaPrecioSelected ;
			evt.cliente = _cliente;
			dispatcher.dispatchEvent(evt);
		}
		
		
		
		public function set cliente(cli:ClienteTransaccion):void{
			_cliente = cli;			
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		public function set arrayCuotas(array:ArrayCollection):void{
			_arrayCuotas = array;
			ordenarCuotas();
			dispatchEvent(new Event("changedArrayCuotas"));
		}
		
		private function ordenarCuotas():void{
			_arrayCuotas.sort = new Sort();
			_arrayCuotas.sort.fields = [new SortField("cantidadCuota")];
			_arrayCuotas.refresh();
		}
		
		[Bindable (event="changedArrayCuotas")]
		public function get arrayCuotas():ArrayCollection{
			return _arrayCuotas;
		}
		
		public function set cuotaSeleccionada(cuota:Cuota):void{
			_cuotaSeleccionada = cuota;
			if(cuota){
			  cantidadCuotaSeleccionada= cuota.cantidadCuota;
			  importeCuota = cuota.interes + cuota.capital;
			}
		}
	
        		
		
		
		public function get cuotaSeleccionada():Cuota{
			return _cuotaSeleccionada;
		}
		
		
		public function set monto(monto:Number):void{
			_monto = monto;
		}
		
		public function get monto():Number{
			return _monto;
		}
		
		public function set disponible(disponible:Object):void{
			_disponible = disponible;
			dispatchEvent(new Event("changedDisponible"));
		}
		
		[Bindable (event="changedDisponible")]
		public function get disponible():Object{
			return _disponible;
		}
		
		public function set listaPrecioSelected(listaPrecioSelected:ListaPrecio):void{
			_listaPrecioSelected = listaPrecioSelected;
			  
			if(_listaPrecioSelected && cliente){
			   habilitarSimular=true;
		      var  arrayItems:  Array =  listaPrecioSelected.versionVigente.itemsListaPrecio;
		      arrayItems.sort(function(o1:Object, o2:Object):int{
		      		return ObjectUtil.compare(ListaPrecioItem(o1).comCuotas,ListaPrecioItem(o2).comCuotas);
		      });
		       cuotasDisponibles="Cuotas Disponibles: [-"; 
			   for each ( var element:ListaPrecioItem in arrayItems)
                {  cuotasDisponibles+= element.comCuotas + "-";
                }
                cuotasDisponibles+= "]"; 
			}
			   
		}
		
		public function get listaPrecioSelected():ListaPrecio{
			return _listaPrecioSelected;
		}
		
		public function onChangeCliente(event:SimuladorEvent):void{
		      habilitarSimular=  false;
	     }
	     
	     
	     public function inicializar():void{
	     	cuotasDisponibles="";
	     }
	     
	    
	
	}	

}