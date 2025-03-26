package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.transacciones.PagoDelegate;
	import com.tarjetafiel.caja.event.GeneralCajaFieldEvent;
	import com.tarjetafiel.caja.event.PagoEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.LiqCliente;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.components.alert.AlertWarning;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.styles.StyleManager;
	import mx.styles.CSSStyleDeclaration;
	import mx.events.CloseEvent;
	import com.util.Imagenes;


	public class PagoCommand implements ICommand, IResponder
	{
		private var functionCallPagoRealizado:Function;
		private var alert:Alert;
		
		
		public function PagoCommand()
		{
		}

		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();			
					
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault == null){
				AlertError.show("La caja no tiene una impresora prdeterminada\nNo podra operar sin impresora");
				ControlBlock.getInstance().remove();
				return;				
			}
			
			if(ModelLocator.getInstance().impresorasModel.impresoraDefault.habilitado == "S"){
				functionCallPagoRealizado = PagoEvent(event).functionCallPagoRealizado;
				try {
					new PagoDelegate(this).registrarPago( PagoEvent(event) );
				} catch(err:Error){
					AlertError.show("Hubo un error en la aplicación\nEl pago no podra efectuarse\n\n" + err.message);
					ControlBlock.getInstance().remove();
				}
			} else {
				AlertError.show("La impresora por defecto no esta habilitada");
				ControlBlock.getInstance().remove();
			} 
			
		}
		
		public function result(data:Object):void {	
		//	var alertw:AlertWarning;
			
			var alertCSS:CSSStyleDeclaration;
			
			new GeneralCajaFieldEvent(GeneralCajaFieldEvent.UPDATE_RECIBO_COBRADOR_EVENT,ModelLocator.getInstance().pagoModel.recibo).dispatch();
						
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().clienteSeleccionado.cliente = RespuestaImpresion(data.result).target as ClienteTransaccion;
			ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion =  RespuestaImpresion(data.result);
			ModelLocator.getInstance().repactacionModel.liqClienteRepactacion = null;
			ModelLocator.getInstance().repactacionModel.arrayRepactaciones.removeAll();
			if(RespuestaImpresion(data.result).falloImpresion){
				AlertWarning.show(RespuestaImpresion(data.result).mensaje);	
			} else {
				ModelLocator.getInstance().ticketModel.ultimaRespuestaImpresion = RespuestaImpresion(data.result);
				if (RespuestaImpresion(data.result).estadoArqueo == 1) {
					
					alertCSS = StyleManager.getStyleDeclaration("mx.controls.Alert");
					
					alertCSS.setStyle("backgroundColor", "red")			
					alertCSS.setStyle('fontFamily', "Arial");
					alertCSS.setStyle('fontSize', 16);
					alertCSS.setStyle('fontWeight', "bold");
					alertCSS.setStyle('fontColor', "white");
					alertCSS.setStyle('color', "white");
					alertCSS.setStyle("fillColors", [ 0xffffff, 0xF5A2A2, 0xF5A2A2, 0xffffff ]);
					
					
					
					/*Alert.show(RespuestaImpresion(data.result).mensaje + "\n\n SOLICITAR O REALIZAR \n DESCARGA DE VALORES");
						
					
						Alert.show(RespuestaImpresion(data.result).mensaje + "\n\n SOLICITAR O REALIZAR \n DESCARGA DE VALORES",,
							function (evt:CloseEvent):void{
							if(evt.detail == Alert.OK){
								alertCSS.setStyle("backgroundColor", "#00388B");
							}
						});	*/	
						
					Alert.show(RespuestaImpresion(data.result).mensaje + "\n\n SOLICITAR O REALIZAR \n DESCARGA DE VALORES"
							,"Mensaje",Alert.OK,null,function (evt:CloseEvent):void{
								if(evt.detail == Alert.OK){
									alertCSS.setStyle("backgroundColor", "#00388B");
									alertCSS.setStyle('fontFamily', "Arial");
									alertCSS.setStyle('fontSize', 12);
									alertCSS.setStyle('fontWeight', "bold");
								}
							},Imagenes.warningImg,null);
					
					/*AlertOk.show(RespuestaImpresion(data.result).mensaje + "\n\n SOLICITAR O REALIZAR \n DESCARGA DE VALORES"
						,function (evt:CloseEvent):void{
							if(evt.detail == Alert.OK){
								alertCSS.setStyle("backgroundColor", "#00388B");
								alertCSS.setStyle('fontFamily', "Arial");
								alertCSS.setStyle('fontSize', 12);
								alertCSS.setStyle('fontWeight', "bold");
							}
						},"Mensaje",Alert.OK);*/
					
					/*show(text:String = "",
						closeHandler:Function = null,
						title:String = "Mensaje",defaultButton:uint=Alert.OK)*/
						
				//alertw.setElementos();
				//alertw.styleName("alertwar");
				
				} else  {
					/*AlertOk.show(RespuestaImpresion(data.result).mensaje);*/
					
					Alert.show(RespuestaImpresion(data.result).mensaje);
				}
				
			}
			
			
						
			functionCallPagoRealizado.call(this);
		}
		
		public function fault(info:Object):void
		{
			for each(var liq:LiqCliente in ModelLocator.getInstance().pagoModel.arrayLiqPagar){
				liq.importePagado = 0;
			}
			ControlBlock.getInstance().remove();
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}