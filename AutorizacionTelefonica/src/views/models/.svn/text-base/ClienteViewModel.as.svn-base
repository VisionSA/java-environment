package views.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.PlasticoCliente;
	
	import events.ClienteEvent;
	
	import flash.display.DisplayObject;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.managers.PopUpManager;
	
	import views.popup.MotivoNoConsumoView;
	
	[Bindable]
	public class ClienteViewModel extends EventDispatcher
	{
		public var dispatcher:IEventDispatcher;
		
		private var _cliente:ClienteTransaccion;
		private var _titular:ClienteTransaccion;
		public var nombreCompleto:String;
		public var mostrarPanelCliente:Boolean = false;
		public var mostrarBusquedaComercio:Boolean = false;
		public var disponible : Object; 
		public var mostrarTitular: Boolean=false ;
		public var nombreCompletoTitular: String; 
		public var plasticoCliente: PlasticoCliente;
		private var _nroPlastico : String;
		public var saldoEnLinea :  Number = 0 ;  
		public var puedeConsumir:String ="";
	    public var popUpMotivoNoConsumo:MotivoNoConsumoView= new MotivoNoConsumoView();
	    public var displayObject :DisplayObject;
	    public var mostrarMotivoDisponible: Boolean=false ;
	    public var mostrarMotivoSituacionCobranza: Boolean=false ;
	    public var mostrarMotivoSituacionComercial: Boolean=false ;
	    public var mostrarMotivoEstadoPlastico: Boolean=false ;
	    public var labelMotivoDisponible: String="";
	    public var labelMotivoSituacionCobranza: String="";
	    public var labelMotivoSituacionComercial: String="";
	    public var labelMotivoEstadoPlastico: String="";
	     
		public function ClienteViewModel()
		{
			
		}
		
		public function buscarPorPlastico(plastico:String, cvv:String,displayObject:DisplayObject):void{			
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_POR_PLASTICO);
			evt.nroTarjeta = plastico;
			evt.codSeguridad = cvv;
			nroPlastico =plastico;
			this.displayObject=displayObject;
			dispatcher.dispatchEvent(evt);
		}
		
		
		public function buscarPorID(id:Number):void{
		   var evt:ClienteEvent= new ClienteEvent(ClienteEvent.BUSCAR_POR_ID); 	
		   evt.id=id;
		   dispatcher.dispatchEvent(evt);
		}
		
		public function set cliente(cli:ClienteTransaccion):void{
			_cliente = cli;
			
			if(_cliente){
				 mostrarPanelCliente = true;
				 if(cli.adicional!=0){
		     	   nombreCompleto= "ADISCIONAL #"+cli.adicional+": "+cli.individuo.apellido+","+cli.individuo.nombres;
		     	   mostrarTitular=true;
		     	   buscarPorID(cli.idTitular);
		     	 }else{
		     	 	titular = cli;
		     	 	mostrarTitular=false
		     	 	nombreCompleto= "TITULAR: "+cli.individuo.apellido+","+cli.individuo.nombres;		     	 	
		     	 	setDatosTitular(); 
		     	 } 
		    } 
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			if(tit){
			    nombreCompletoTitular= tit.individuo.apellido+","+tit.individuo.nombres;
			    saldoEnLinea = new Number(tit.saldoLinea) ; //el adiscional toma el saldo en linea del titular 
			    //disponible = Number(_titular.limiteCredito) - saldoEnLinea;
			    setDatosTitular();
			}
		}
		
		public function setDatosTitular():void{
			 
			 plasticoCliente=  cliente.platicoClienteHabilitado;
			 saldoEnLinea = new  Number(_titular.saldoLinea);
			 disponible = Number(_titular.limiteCredito) - saldoEnLinea;
			 if(cliente.habilitadoConsumo== "H") 
			 	puedeConsumir= "SI"
			 else puedeConsumir= "NO"
				
			 if(titular.limiteCredito<=0 || titular.habilitadoConsumo== "D" || plasticoCliente.estadoPlastico.idPlasticoEstado !=1)
			 { 
				mostrarBusquedaComercio=false;
				if(titular.habilitadoConsumo == "D"){
					mostrarMotivoSituacionComercial=true;
				  	labelMotivoSituacionComercial= cliente.estadoCliente.descripcion;
				}
				if(titular.estadoCobranza.habilitadoConsumo == "N"){
				     mostrarMotivoSituacionCobranza=true;
				     labelMotivoSituacionCobranza= cliente.estadoCobranza.descripcion;
				 }
				 if(disponible<=0){
				     mostrarMotivoDisponible=true;
				     labelMotivoDisponible = "Saldo  Insuficiente";
				 }
				 if(plasticoCliente.estadoPlastico.idPlasticoEstado !=1){
				     mostrarMotivoEstadoPlastico=true;
				     labelMotivoEstadoPlastico =  plasticoCliente.estadoPlastico.descripcion;
				 }
			     
			     PopUpManager.addPopUp(popUpMotivoNoConsumo,displayObject, true);
	             PopUpManager.centerPopUp(popUpMotivoNoConsumo);
	          
	          } else {
	          	  mostrarBusquedaComercio=  true;
	          }
	 			 
		}    	
		
	
		
		public function get titular():ClienteTransaccion{
			return _titular;
		}
		
		public function set nroPlastico(nro:String):void{
			_nroPlastico = nro;
		}
		
		public function get nroPlastico():String{
			return _nroPlastico;
		}
		
		
		public function inicializar():void{
			mostrarPanelCliente= false;	
			cliente= null;
			mostrarBusquedaComercio=false;
					
		}
		

	}
}