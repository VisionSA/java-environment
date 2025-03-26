package com.tarjetafiel.caja.model
{
	import com.adobe.cairngorm.model.IModelLocator;
	import com.tarjetafiel.caja.view.comercios.LiqComercioDetView;
	import com.tarjetafiel.caja.view.comercios.MasDatosEmpresa;
	

	public class ModelLocator implements IModelLocator
	{
		private static var instance:ModelLocator = null;			
		
		[Bindable]public var busquedaVisible:int = 0;
		
		[Bindable]public var busquedaVisibleComercios:int = 0;
		
		public static function getInstance():ModelLocator
		{
			if(instance == null){ instance = new ModelLocator() }
			return instance;
		}	

		//<--- PopUps --->
			public var clientesEncontradosView:ClientesEncontradosViewModel = new ClientesEncontradosViewModel();
			
			/*public var empresasEncontradasView:EmpresasEncontradasView = new EmpresasEncontradasView();*/
			
			/*public var masDatosEmpresa:MasDatosEmpresa = new MasDatosEmpresa();*/
			
			/*public var liqComercioDetView:LiqComercioDetView = new LiqComercioDetView();*/					
			
			public var mensajes:Mensajes = new Mensajes();					
																	
			[Bindable]public var accesoDenegadoModel:AccesoDenegadoModel = new AccesoDenegadoModel();
		//<--- PopUps --->
		
		//<--- Logica --->
				
			//public var controlBlock:ControlBlock = new ControlBlock();
		
			//public var managerModules:ManagerModules = new ManagerModules();
			
			//public var ventanasAbiertas:VentanasAbiertasModel = new VentanasAbiertasModel();
						
		//<--- Logica --->
	
		//<--- Modelo del dominio --->		
		[Bindable]public var clienteSeleccionado:ClienteModel = new ClienteModel();																		
		
		[Bindable]public var empresaModel:EmpresaModel = new EmpresaModel();
					
		[Bindable]public var bancoModel:BancoModel = new BancoModel();
		
		[Bindable]public var liqComercioModel:LiqComercioModel = new LiqComercioModel();
		
		[Bindable]public var operadorModel:OperadorModel = new OperadorModel();			
						
		[Bindable]public var liqClienteModel:LiqClienteModel = new LiqClienteModel();  
		
		[Bindable]public var pagoModel:PagoModel = new PagoModel();
		
		[Bindable]public var conceptosModel:ConceptosModel = new ConceptosModel();
		
		[Bindable]public var cajaModel:CajaModel = new CajaModel();
		
		[Bindable]public var formaPagoCajaModel:FormaPagoCajaModel= new FormaPagoCajaModel();
		
		[Bindable]public var chequeEstadoModel:ChequeEstadoModel = new ChequeEstadoModel();
		
		[Bindable]public var movimientoModel:MovimientoModel = new MovimientoModel();
		
		[Bindable]public var repactacionModel:RepactacionModel = new RepactacionModel();
		
		[Bindable]public var adelantoEfectivoModel:AdelantoEfectivoModel = new AdelantoEfectivoModel();
		
		[Bindable]public var impresorasModel:ImpresorasModel = new ImpresorasModel();
		
		[Bindable]public var chequeModel:ChequeModel = new ChequeModel(); 
		
		[Bindable]public var arqueoCajaModel:ArqueoCajaModel = new ArqueoCajaModel();
		
		[Bindable]public var navegacionModel:NavegacionModel = new NavegacionModel();
		
		[Bindable]public var descargaValoresModel:DescargaDeValoresModel = new DescargaDeValoresModel();
		
		[Bindable]public var ticketModel:TicketModel = new TicketModel();
		
		[Bindable]public var pagosAComercios:PagoAComercioModel = new PagoAComercioModel();
		
		[Bindable]public var individuosHabilitadosPagosModel:IndividuosHabilitadosPagosModel = new IndividuosHabilitadosPagosModel();
		
		[Bindable]public var depositoBancarioModel:DepositoBancarioModel = new DepositoBancarioModel();
		
		[Bindable]public var tarjetaDebitoModel:TarjetaDebitoModel = new TarjetaDebitoModel();
		
		//<--- Modelo del dominio --->
		
		
		
		public function busquedaClientesVisible():void{
			busquedaVisible = 150;
		}
		
		public function busquedaClientesNoVisible():void{
			busquedaVisible = 0;
		}
		
		public function busquedaComerciosVisible():void{
			busquedaVisibleComercios = 150;
		}
		
		public function busquedaComerciosNoVisible():void{
			busquedaVisibleComercios = 0;
		}
				
	}
}