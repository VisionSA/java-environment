package views.categorias.comercio.renderers
{
	import mx.controls.Label;
	import mx.core.IFactory;
	
	public class DebeHaberRenderer extends Label implements IFactory
	{
		public function DebeHaberRenderer()
		{
		}
		
		public function newInstance():*{
			return new DebeHaberRenderer();
		}
		
		protected override function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void{
			super.updateDisplayList(unscaledWidth,unscaledHeight);
			
			if(data){
				if(data.signo == -1){
					this.setStyle("color","#DD0015");
				} else {
					this.setStyle("color","#198600");
				}
				
				this.setStyle("fontWeight","bold");
				this.setStyle("fontSize","14");
			}
		}

	}
}