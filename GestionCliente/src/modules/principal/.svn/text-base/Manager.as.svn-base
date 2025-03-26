package modules.principal
{
	import com.tarjetafiel.caja.vo.ArchivoFarmacia;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.GestionCliente;
	import com.tarjetafiel.caja.vo.GestionClienteLog;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.Provincia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.IEventDispatcher;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.utils.ByteArray;
	
	import modules.principal.popup.AsignarCliente;
	import modules.principal.popup.CambiarEstado;
	import modules.principal.popup.GenerarInforme;
	import modules.principal.popup.ReasignarCliente;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.Fault;
	
	
	[Bindable]
	public class Manager
	{
		public var dispatcher:IEventDispatcher;
		private var filtro:Filtro;
		public var paginador:Paginador;
		private var oFile : FileReference;
		
		public var gestionesList:ArrayCollection;
		public var gestionesVigentesList:ArrayCollection;
		public var gestionesVencidasList:ArrayCollection;
		public var gestionesConConsumoList:ArrayCollection;
		public var gestionesSinConsumoList:ArrayCollection;
		public var nombreCliente:String;
		
		public var operador:Operador;
		
		//public var idOperador:Number;
		//public var nomOperador:String;
		//public var apOperador:String;
		public var estadoActual:Number;
		public var idGestionSelected:Number;
		public var idClienteSelected:Number;
		
		public var popUpAsignarCliente:AsignarCliente = new AsignarCliente();
		public var popUpReasignarCliente:ReasignarCliente = new ReasignarCliente();
		public var popUpCambiarEstado:CambiarEstado = new CambiarEstado();
		public var popUpGenerarInforme:GenerarInforme = new GenerarInforme();
		

public var lugarDescripcion:String;	
		
		public function Manager()
		{
			 
		}
		
		public function abrirPopUpAsignarCliente():void{
			popUpAsignarCliente.manager = this;
			popUpAsignarCliente.initCombo();
			this.nombreCliente="";
			PopUpManager.addPopUp(popUpAsignarCliente,Application.application as  DisplayObject, true);
			popUpAsignarCliente.txtIdCliente.text = "";
			PopUpManager.centerPopUp(popUpAsignarCliente);
		}  
		
		public function abrirPopUpReasignarCliente():void{
			popUpReasignarCliente.manager = this;
			popUpReasignarCliente.initCombo();
			PopUpManager.removePopUp(popUpReasignarCliente);
			PopUpManager.addPopUp(popUpReasignarCliente,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpReasignarCliente);
		}  
		
		public function abrirPopUpGenerarInforme():void{
			popUpGenerarInforme.manager = this;
			PopUpManager.addPopUp(popUpGenerarInforme,Application.application as  DisplayObject, true);
			popUpGenerarInforme.cleanData();
			PopUpManager.centerPopUp(popUpGenerarInforme);
		}  
		
		public function abrirPopUpCambiarEstado():void{
			popUpCambiarEstado.manager = this;
			popUpCambiarEstado.initCombo();
			PopUpManager.removePopUp(popUpCambiarEstado);
			PopUpManager.addPopUp(popUpCambiarEstado,Application.application as  DisplayObject, true);
			//popUpCambiarEstado.cmbEstados.selectedIndex = estadoActual - 1;
			switch(estadoActual){
				case 1:
					popUpCambiarEstado.cmbEstados.selectedIndex = 0; //Gestionar Cliente
					break;
				case 2:
					popUpCambiarEstado.cmbEstados.selectedIndex = 1; //Gestionando Cliente
					break;
				case 3:
					popUpCambiarEstado.cmbEstados.selectedIndex = 3; //F. Con Contacto
					break;
				case 4:
					popUpCambiarEstado.cmbEstados.selectedIndex = 4; //F. Sin Contacto
					break
				case 5:
					popUpCambiarEstado.cmbEstados.selectedIndex = 2; //Llamar Nuevamente
			}
			PopUpManager.centerPopUp(popUpCambiarEstado);
		}  
		
		public function getIndice():Number{
			var i:Number = 0;
			while(idGestionSelected != Number(gestionesList.getItemAt(i).idGestion)){
				i++;
			}
			return i;
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		
		public function buscarNombreCliente(idCliente:Number):void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_NOMBRE_CLIENTE);
			evt.idCliente = idCliente;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultNombreCliente(result:String):void{			
			nombreCliente = result.toString();			
			ControlBlock.getInstance().remove();
			if(!result){
				AlertError.show("Cliente no existe");
			}
		}
		
		
		public function guardarGestionCliente(gestionCliente:GestionCliente, gestionClienteLog:GestionClienteLog):void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.GUARDAR_GESTION_CLIENTE);
			evt.gestionCliente = gestionCliente;
			evt.gestionClienteLog = gestionClienteLog;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultGuardarGestionCliente(result:String):void{					
			ControlBlock.getInstance().remove();
			if(result=="Cliente Asignado Correctamente."){
				AlertOk.show(result);
			}
			else if(result=="Cliente Descartado Correctamente."){
				AlertOk.show(result);
			}
			else{
				AlertError.show(result);
			}
		}
		
		
		public function guardarGestionClienteLog():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.GUARDAR_GESTION_CLIENTE_LOG);
			var gestionClienteLog:GestionClienteLog = new GestionClienteLog();
			gestionClienteLog.idGestion = idGestionSelected;
			//gestionClienteLog.idOperador = idOperador;
			gestionClienteLog.idOperador = operador.codigo;
			gestionClienteLog.idEstadoGestion = estadoActual;
			evt.gestionClienteLog = gestionClienteLog;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultGuardarGestionClienteLog(result:String):void{					
			ControlBlock.getInstance().remove();
			AlertOk.show(result);
		}
		
		
		public function guardarLog(log:String):void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.GUARDAR_LOG);
			evt.log = log;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultGuardarLog(result:String):void{					
			ControlBlock.getInstance().remove();
		}
		

		public function generarInforme(fDesde:Date, fHasta:Date, idOperador:Number):void {
			//urlJump("report/GestionCliente.jrxml?AExcel=excel");
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.GENERAR_INFORME);
			evt.fDesde = fDesde;
			evt.fHasta = fHasta;
			evt.idOpInforme = idOperador;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
			
		}
		public function resultInformeGestionCliente(result:String):void{		
			ControlBlock.getInstance().remove();
			if(result.toString() != null) urlJump(result.toString());
			else AlertError.show("Error al generar Informe");
		}
		
		
		private function urlJump(page:String):void{
			var url:URLRequest = new URLRequest(page);
			navigateToURL(url, "_blank" );
		}    
		
		
		
		
		public function buscarGestionCliente():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_CLIENTE);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionCliente(result:Array):void{
			gestionesList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}
		
		
		public function buscarGestionClienteByIdOperador(idOperador:Number):void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_CLIENTE_BY_ID_OP);
			evt.idOperador = idOperador;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionClienteByIdOperador(result:Array):void{			
			gestionesList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}
		
		
		public function buscarGestionVigente():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_VIGENTE);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionVigente(result:Array):void{
			gestionesVigentesList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}
		
		public function buscarGestionVencida():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_VENCIDA);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionVencida(result:Array):void{
			gestionesVencidasList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}
		
		
		public function buscarGestionConConsumo():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_CONCONSUMO);
			evt.idOperador = operador.codigo;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionConConsumo(result:Array):void{			
			gestionesConConsumoList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}
		
		
		public function buscarGestionSinConsumo():void{
			var evt:GestionClienteEvent = new GestionClienteEvent(GestionClienteEvent.BUSCAR_GESTION_SINCONCUMO);
			evt.idOperador = operador.codigo;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		public function resultListGestionSinConsumo(result:Array):void{			
			gestionesSinConsumoList = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertError.show("No se encontraron datos");
			}
		}

	}
}