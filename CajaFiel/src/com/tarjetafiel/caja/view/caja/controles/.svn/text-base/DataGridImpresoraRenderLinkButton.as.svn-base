package com.tarjetafiel.caja.view.caja.controles
{
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.Impresora;
	import com.util.components.alert.AlertYesNo;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.LinkButton;
	import mx.core.IFlexDisplayObject;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;

	public class DataGridImpresoraRenderLinkButton extends LinkButton
	{				
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
          
          	this.setStyle("color","#000000");
			if(data){

				this.label = "Predeterminada";
			
    	        if(ModelLocator.getInstance().impresorasModel.impresoraDefault && Impresora(data).idImpresora == ModelLocator.getInstance().impresorasModel.impresoraDefault.idImpresora){
        	    	this.setStyle("color","#FF0018");
            	}
            	Impresora(data).addEventListener("changedPredeterminada",refreshColor);
   			}
   			
   			super.updateDisplayList(unscaledWidth, unscaledHeight);
			
        }
        
        private function refreshColor(evt:Event):void{
        	this.setStyle("color",data.colorPredetermianda);
        }
        
                
        override protected function clickHandler(event:MouseEvent):void{
        	AlertYesNo.show("¿Confirma la impresora seleccionada como predeterminada?",function (evt:CloseEvent):void{
					if(evt.detail == Alert.YES)
						var imps:ArrayCollection = ModelLocator.getInstance().impresorasModel.impresorasList;
						for each(var imp:Impresora in imps){
							if(imp.idImpresora == data.idImpresora){
								ModelLocator.getInstance().impresorasModel.impresoraDefault = imp;
								imp.colorPredetermianda = "#FF0018";								
							} else {
								imp.colorPredetermianda = "#000000";
							}	
							imp.dispatchEvent(new Event("changedPredeterminada"));
						}
						
						PopUpManager.removePopUp(parent.parent.parent.parent.parent as IFlexDisplayObject);
						
			});			
        }
		
	}
}