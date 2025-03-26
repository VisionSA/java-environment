package com.tarjetafiel.caja.view.clientes.controles
{
	import com.util.format.FormatUtil;
	
	import mx.controls.Label;
			
	public class ItemRenderValorRepactacion extends Label {

        
        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
            super.updateDisplayList(unscaledWidth, unscaledHeight);

            if(data != null && data.valor is Number){
            	text = FormatUtil.formatMoneda(data.valor);
            }
        }
    }

}