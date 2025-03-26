package views.categorias.cliente.models
{
	import flash.display.Graphics;
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.CursorBookmark;
	import mx.collections.IHierarchicalCollectionView;
	import mx.collections.IViewCursor;
	import mx.controls.AdvancedDataGrid;
	import mx.controls.Alert;
	import com.tarjetafiel.caja.vo.MovimientoCtaCte;
	import mx.core.FlexShape;
	import mx.rpc.events.AbstractEvent;
	import mx.utils.ObjectUtil;
	
	
	/**
	 * This is an extended version of the built-in Flex datagrid. 
	 * This extended version has the correct override logic in it
	 * to draw the background color of the cells, based on the value of the
	 * data in the row.
	 **/
	public class CustomRowColorAdvancedDataGrid extends AdvancedDataGrid
	{
		private var _rowColorFunction:Function;
		
		private var fecha:Date;
		
		
		
		public function CustomRowColorAdvancedDataGrid()
		{
			super();
		}
		
		
		/**
		 * A user-defined function that will return the correct color of the
		 * row. Usually based on the row data.
		 * 
		 * expected function signature:
		 * public function F(item:Object, defaultColor:uint):uint
		 **/
		public function set rowColorFunction(f:Function):void
		{
			this._rowColorFunction = f;
		}
		
		public function get rowColorFunction():Function
		{
			return this._rowColorFunction;
		}
		
		
		
		private var displayWidth:Number; // I wish this was protected, or internal so I didn't have to recalculate it myself.           
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.updateDisplayList(unscaledWidth, unscaledHeight);         
			if (displayWidth != unscaledWidth - viewMetrics.right - viewMetrics.left)
			{
				displayWidth = unscaledWidth - viewMetrics.right - viewMetrics.left;
			}
		}
		
		
		/**
		 *  Draws a row background 
		 *  at the position and height specified using the
		 *  color specified.  This implementation creates a Shape as a
		 *  child of the input Sprite and fills it with the appropriate color.
		 *  This method also uses the <code>backgroundAlpha</code> style property 
		 *  setting to determine the transparency of the background color.
		 * 
		 *  @param s A Sprite that will contain a display object
		 *  that contains the graphics for that row.
		 *
		 *  @param rowIndex The row's index in the set of displayed rows.  The
		 *  header does not count, the top most visible row has a row index of 0.
		 *  This is used to keep track of the objects used for drawing
		 *  backgrounds so a particular row can re-use the same display object
		 *  even though the index of the item that row is rendering has changed.
		 *
		 *  @param y The suggested y position for the background
		 * 
		 *  @param height The suggested height for the indicator
		 * 
		 *  @param color The suggested color for the indicator
		 * 
		 *  @param dataIndex The index of the item for that row in the
		 *  data provider.  This can be used to color the 10th item differently
		 *  for example.
		 */
		override protected function drawRowBackground(s:Sprite, rowIndex:int,
													  y:Number, height:Number, color:uint, dataIndex:int):void
		{
				
		
			
			/*if( this.rowColorFunction != null )
			{*/
			
			
			
			if( this.dataProvider != null )
			{
				
				
				
				if( dataIndex < this.dataProvider.length)
				{
					/*if (dataIndex == 0) {
						Alert.show("dataIndex " + dataIndex);
						var tmpObj1:ArrayCollection = (this.dataProvider as ArrayCollection);
						var item1:Object = tmpObj.getItemAt(dataIndex);
						fecha = item1.fechaFacturacion;
						Alert.show("fecha " + fecha);
						
					}*/
					/*Alert.show("dataProvider " + this.dataProvider.length);*/
					try {
						var tmpObj:ArrayCollection = (this.dataProvider as ArrayCollection);
						var item:MovimientoCtaCte = ((MovimientoCtaCte)(tmpObj.getItemAt(dataIndex)));
						/*if( item != null && item.estadoImpacto == 'Pendiente' ) color = 0xFFFFFF;
						else color = 0xCCFFFF;*/
						
							/*Alert.show("item.fechaFacturacion " + item.fechaFacturacion);
							Alert.show("fecha " + fecha);*/
						
						
						//if( item != null && (ObjectUtil.dateCompare(item.fechaFacturacion, fecha) == 0) ) 
							if( item != null && (item.color == 0 || item.color == 2) ) 
						{
								color == 0xFFFFFF;
						}
						else {
							
								color = 0xCCFFFF;
								//color = 0xCCFFFF;
							
							
							//color = colorLinea;
							
							/*Alert.show("item.fechaFacturacion " + item.fechaFacturacion);
							Alert.show("fecha " + fecha);*/
						
						}
						
						
						//Alert.show("drawRowBackground");
						//color = this.rowColorFunction.call(this, item, color);
					} catch(error:Error) {
						try {
							var tmpView:IHierarchicalCollectionView;
							tmpView = (this.dataProvider as IHierarchicalCollectionView);
							var tmpCursor:IViewCursor = tmpView.createCursor();
							tmpCursor.seek(CursorBookmark.FIRST,dataIndex);
							var itemX:Object = tmpCursor.current;
							color = this.rowColorFunction.call(this, itemX, color);
						} catch(error:Error) {
							Alert.show(error.message);
						}
					}
				}
			}
			
			super.drawRowBackground(s, rowIndex, y, height, color, dataIndex);
		}
		
		
	}
}
