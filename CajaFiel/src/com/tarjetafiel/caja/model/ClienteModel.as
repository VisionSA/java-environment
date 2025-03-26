package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.ClientesEvent;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	public class ClienteModel extends EventDispatcher
	{			
		
		[Bindable]private var _cliente:ClienteTransaccion;		
		[Bindable]public var clienteAdicional:ClienteTransaccion;
		[Bindable]public var clienteSelect:String = "No hay un cliente seleccionado";
		[Bindable]public var labelButtonBusqueda:String = "Buscar"; 
		//Variables que se utilizan en la pantalla AdelantoEfectivo
		//Es el dataprovider de la grilla de adelanto en efectivo
		[Bindable]public var arrayOperaciones:ArrayCollection = new ArrayCollection();				
		[Bindable]public var disponibleActual:Object;
		[Bindable]public var saldoActual:Object;
		[Bindable]public var disponible:Object;
		[Bindable]public var futurosVencimientos:ArrayCollection = new ArrayCollection();
		//Variables que se utilizan en la pantalla AdelantoEfectivo		
		
		public static const CUENTA:String = "cuenta";
		public static const OTROS_PARAM:String = "otrosParam";
		
		private var buscarPor:String;
		private var filtro:Filtro = new Filtro();		
		
		[Bindable]public var labelEstadoComercial:String;
		[Bindable]public var labelEstadoCobranza:String;
	
		public function set cliente(cli:ClienteTransaccion):void{			
			_cliente = cli;
			labelEstadoComercial = this.getEstadoCliente();
			labelEstadoCobranza = this.setEstadoCobranza(_cliente.estadoCobranza.idEstadoCobranza.toString());
			this.calcularSaldo();
			dispatchEvent(new Event("reiniciar"));
			dispatchEvent(new Event("changedCliente"));
		}		
		
		public function calcularSaldo():void{
			disponible = Number(_cliente.limiteCredito) - Number(_cliente.saldoLinea);
			if (disponible < 0) {
				disponible = 0;
			}					
		}
		
		public function reIniciar():void{
			this.saldoActual = "";
			this.disponibleActual = "";
			this.arrayOperaciones.removeAll();			
		}
		
		[Bindable (event="changedCliente")]
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}				
		
		public function set montoTransaccion(monto:Number):void{
			if(monto == 0){
				this.saldoActual = "";
				this.disponibleActual = "";
			} else {				
				this.saldoActual = Number(this._cliente.saldoLinea) + monto;
				this.disponibleActual = Number(_cliente.limiteCredito) - Number(this.saldoActual);
			}
		}			
					
		public function setEstadoCobranza(estado:String):String{
			
			switch(estado){
				case "1":
					return "Normal";
				case "2":
					return "Mora";
				case "3":
					return "Mora";
				case "4":
					return "Mora";
				case "5":
					return "Extrajudicial";
				case "6":
					return "Extrajudicial con convenio";
				case "7":
					return "Judicial";
			}			
			return "No Definido";
		}
		
		public function getEstadoCliente():String{
				 
				switch(cliente.estadoCliente.idEstadoCliente.toString()){
					case "1":
						return "NORMAL";
					case "2":
						return "REFINANCIACION";
					case "3":
						return "BLOQUEADA";
					case "4":
						return "CERRADA";
					case "5":
						return "MIGRACION";
						
				}				
				
				return "No definido";
			}
		
		public function buscar(paginador:Paginador, buscarPor:String=""):void{			
			var typeEvent:String;
			
			if(buscarPor != "")
				this.buscarPor = buscarPor;
			
			if(this.buscarPor == CUENTA){
				typeEvent = ClientesEvent.BUSCAR_CLIENTES_CUENTA;
			} else {
				typeEvent = ClientesEvent.BUSCAR_CLIENTES;
			}
			var evt:ClientesEvent = new ClientesEvent(typeEvent, filtro, paginador);
			evt.dispatch();
		}
		
		public function armarFiltro(plastico:String=null, apellido:String=null, cuit:String=null, nroDoc:String=null,nombre:String=null,nroCuenta:String=null):void{
			this.filtro.campos = new Array();
			this.filtro.operadores = new Array();
			this.filtro.valores = new Array();
													
			if(plastico != null){
				this.filtro.campos.push("numero");
				this.filtro.operadores.push(Filtro.LIKE);
				this.filtro.valores.push(plastico);
				
			} 
			if(apellido != null){
				this.filtro.campos.push("individuo.apellido");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(apellido);
			}				
			
			if(nombre != null){
				this.filtro.campos.push("individuo.nombres");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(nombre);
			}
				
			if(cuit != null){
				this.filtro.campos.push("individuo.cuil");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(cuit);
			}				
					
			if(nroDoc != null){
				this.filtro.campos.push("individuo.nroDocumento");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(nroDoc);
			}
			
			if(nroCuenta != null){
				this.filtro.campos.push("idCliente");
				this.filtro.operadores.push(Filtro.IGUAL);
				this.filtro.valores.push(Number(nroCuenta));
			}
			
								
		}

	}
}