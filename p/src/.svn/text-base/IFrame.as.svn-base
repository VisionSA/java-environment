package
{
	import flash.geom.Point;
	import flash.events.Event;
	import flash.utils.Dictionary;
	import flash.display.DisplayObjectContainer;
	
	import mx.core.Container;
	import mx.events.IndexChangedEvent;
	
	
	import flash.external.ExternalInterface;
	
	public class IFrame extends Container
	{
		private var __source: String;
		private var frameId:String;
		private var iframeId:String;
		
		private var containerDict:Object = null;
		private var settingDict:Object = null;
		
		 //
		 // Here we define javascript functions which will be inserted into the DOM
		 // 
		 //
		private static var FUNCTION_CREATEIFRAME:String = 
			"document.insertScript = function ()" +
			"{ " +
			"if (document.createIFrame==null)" + 
			"{" + 
			"createIFrame = function (frameID)" +
			"{ " +
			"var bodyID = document.getElementsByTagName(\"body\")[0];" +
			"var newDiv = document.createElement('div');" +
			"newDiv.id = frameID;" +
			"newDiv.style.position ='absolute';" +
			"newDiv.style.backgroundColor = 'transparent';" + 
			"newDiv.style.border = '0px';" +
			"newDiv.style.visibility = 'hidden';" +
			"bodyID.appendChild(newDiv);" +
			"}" +
			"}" +
			"}";
		
		private static var FUNCTION_MOVEIFRAME:String = 
			"document.insertScript = function ()" +
			"{ " +
			"if (document.moveIFrame==null)" +
			"{" +
			"moveIFrame = function(frameID, iframeID, x,y,w,h) " + 
			"{" +
			"var frameRef=document.getElementById(frameID);" +
			"frameRef.style.left=x;" + 
			"frameRef.style.top=y;" +
			"var iFrameRef=document.getElementById(iframeID);" +
			"iFrameRef.width=w;" +
			"iFrameRef.height=h;" +
			"}" +
			"}" +
			"}";
		
		private static var FUNCTION_HIDEIFRAME:String = 
			"document.insertScript = function ()" +
			"{ " +
			"if (document.hideIFrame==null)" +
			"{" +
			"hideIFrame = function (frameID)" +
			"{" +
			"document.getElementById(frameID).style.visibility='hidden';" +
			"}" +
			"}" +
			"}";
		
		private static var FUNCTION_SHOWIFRAME:String = 
			"document.insertScript = function ()" +
			"{ " +
			"if (document.showIFrame==null)" +
			"{" +
			"showIFrame = function (frameID)" +
			"{" +
			"document.getElementById(frameID).style.visibility='visible';" +
			"}" +
			"}" +
			"}";
		
		private static var FUNCTION_LOADIFRAME:String = 
			"document.insertScript = function ()" +
			"{ " +
			"if (document.loadIFrame==null)" +
			"{" +
			"loadIFrame = function (frameID, iframeID, url)" +
			"{" +
			"document.getElementById(frameID).innerHTML = \"<iframe id='\"+iframeID+\"' src='\"+url+\"' frameborder='0'></iframe>\";" +
			"}" +
			"}" +
			"}";
		
		
		 //
		 // Constructor
		 //
		 //
		public function IFrame()
		{
			super();
		}
		
		 //
		 // Generate DOM elements and build display path.
		 //
		 //
		override protected function createChildren():void
		{
			super.createChildren();
			
			if (! ExternalInterface.available)
			{
				throw new Error("ExternalInterface is not available in this container. Internet Explorer ActiveX, Firefox, Mozilla 1.7.5 and greater, or other browsers that support NPRuntime are required.");
			}
			
			// Generate unique id's for frame div name
			frameId = id;
			iframeId = "iframe_"+frameId;
			
			// Add functions to DOM if they aren't already there
			ExternalInterface.call(FUNCTION_CREATEIFRAME);
			ExternalInterface.call(FUNCTION_MOVEIFRAME);
			ExternalInterface.call(FUNCTION_HIDEIFRAME);
			ExternalInterface.call(FUNCTION_SHOWIFRAME);
			ExternalInterface.call(FUNCTION_LOADIFRAME);
			
			// Insert frame into DOM using our precreated function 'createIFrame'
			ExternalInterface.call("createIFrame", frameId);
			
			buildContainerList();
		}
		
		 //
		 // Build list of container object on the display list path all the way down
		 // to this object. We will seed the container classed we find with an event
		 // listener will be used to test if this object is to be displayed or not.
		 //
		
		private function buildContainerList():void
		{
			// We are going to store containers against index of child which leads down
			// to IFrame item.
			containerDict = new Dictionary();
			settingDict = new Dictionary();
			
			var current:DisplayObjectContainer = parent;
			var previous:DisplayObjectContainer = this;
			
			while (current!=null)
			{
				if (current is Container)
				{
					if (current.contains(previous))
					{
						var childIndex:Number = current.getChildIndex(previous);                
						
						// Store child index against container
						containerDict[current] = childIndex;
						settingDict[current] = childIndex;
						
						// Tag on a change listener             
						current.addEventListener(IndexChangedEvent.CHANGE, handleChange);
						
					}
					
				}        
				
				previous = current;
				current = current.parent;
			}
			
		}
		
		 //
		 // Triggered by one of our listeners seeded all the way up the display
		 // list to catch a 'changed' event which might hide or display this object.
		 // 
		 // @param event Event trigger
		 //
		 //
		private function handleChange(event:Event):void
		{
			var target:Object = event.target;
			
			if (event is IndexChangedEvent)
			{
				var changedEvent:IndexChangedEvent = IndexChangedEvent(event)
				var newIndex:Number = changedEvent.newIndex;
				visible = checkDisplay(target, newIndex);
			}
		}
		
		 //
		 // This function updates the selected view child of the signalling container
		 // and then compares the path from our IFrame up the displaylist to see if
		 // the index settings match. Only an exact match all the way down to our
		 // IFrame will satisfy the condition to display the IFrame contents.
		 //
		 // @param target Object event source
		 // @param newIndex Number index from target object.
		 // 
		 //
		
		private function checkDisplay(target:Object, newIndex:Number):Boolean
		{
			var valid:Boolean = false;
			
			if (target is Container)
			{
				var container:DisplayObjectContainer = DisplayObjectContainer(target);
				// Update current setting
				settingDict[container] = newIndex;
				
				valid = true;
				
				for (var item:Object in containerDict)
				{
					var index:Number = lookupIndex(item as Container);
					var setting:Number = lookupSetting(item as Container);
					valid = valid&&(index==setting);
				}
				
			}
			
			return valid;
		}
		
		 //
		 // Return index of child item on path down to this object. If not
		 // found then return -1;
		 //
		 // @param target Container object
		 // 
		 //
		public function lookupIndex(target:Container):Number
		{
			var index:Number = -1;
			
			try
			{
				index = containerDict[target];
			}
			catch (e:Error)
			{
				// Error not found, we have to catch this or a silent exception
				// will be thrown.
				trace(e);
			}
			
			return index;
		}
		

		public function lookupSetting(target:Container):Number
		{
			var index:Number = -1;
			
			try
			{
				index = settingDict[target];
			}
			catch (e:Error)
			{
				// Error not found, we have to catch this or a silent exception
				// will be thrown.
				trace(e);
			}
			
			return index;
		}                
		
	
	
		private function moveIFrame(): void
		{
			
			var localPt:Point = new Point(0, 0);
			var globalPt:Point = this.localToGlobal(localPt);
			
			ExternalInterface.call("moveIFrame", frameId, iframeId, globalPt.x, globalPt.y, this.width, this.height);
		}
		

		override protected function commitProperties():void
		{
			super.commitProperties();
			
			if (source)
			{
				ExternalInterface.call("loadIFrame", frameId, iframeId, source);
				
				// Trigger re-layout of iframe contents.	            
				invalidateDisplayList();
			}
		}        
		

		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			
			moveIFrame();
		}
		

		public function set source(source: String): void
		{
			if (source)
			{
				__source = source;
				
				invalidateProperties();
			}
		}
		

		public function get source(): String
		{
			return __source;
		}
		

		override public function set visible(visible: Boolean): void
		{
			super.visible=visible;
			
			if (visible)
			{
				ExternalInterface.call("showIFrame", frameId);
			}
			else 
			{
				ExternalInterface.call("hideIFrame", frameId);
			}
		}
	}
} 


/*package
{
	import flash.events.Event;
	import flash.external.ExternalInterface;
	import flash.geom.Point;
	
	import mx.containers.Canvas;
	
	public class IFrame extends Canvas
	{
		public var methodResize:String = "FlexIFrame.resize";
		public var methodMove:String = "FlexIFrame.move";
		public var methodNavigate:String = "FlexIFrame.navigate";
		public var methodVisibility:String = "FlexIFrame.visibility";
		
		public var positionChanged:Boolean = false;
		public var sizeChanged:Boolean = false;
		
		private var _autoResize:Boolean = false;
		private var _url:String = '';
		
		public function IFrame()
		{
			super();
			addEventListener(Event.ADDED_TO_STAGE, addedToStageHandler)
		}
		
		override public function set id(value:String):void
		{
			super.id = value;
		}
		
		protected function addedToStageHandler(event:Event):void
		{
			positionChanged = true;
			sizeChanged = true;
			invalidateProperties();
		}
		
		public function set autoResize(value:Boolean):void
		{
			_autoResize = value;
			if(value)
				addEventListener(Event.RESIZE, autoResizeHandler);
			else
				removeEventListener(Event.RESIZE, autoResizeHandler);
		}
		
		public function get autoResize():Boolean
		{
			return _autoResize;
		}
		
		protected function autoResizeHandler(event:Event):void
		{
			positionChanged = true;
			sizeChanged = true;
			invalidateProperties();
		}
		
		override protected function commitProperties():void
		{
			super.commitProperties();
			
			if(positionChanged)
			{
				var point:Point = localToGlobal(new Point(0, 0));
				callJS(methodMove, point.x, point.y);
				positionChanged = false;
			}
			
			if(sizeChanged)
			{
				callJS(methodResize, width, height);
				sizeChanged = false;
			}
		}
		
		public function set url(value:String):void
		{
			_url = value;
			callJS(methodNavigate, value)
		}
		
		public function get url():String
		{
			return _url;
		}
		
		protected function callJS(method:String, ... values):void
		{
			if(!id)
				throw new Error("IFrame id is not defined");
			var args:Array = [method, id];
			try
			{
				ExternalInterface.call.apply(ExternalInterface, args.concat(values));
			}
			catch(error:Error)
			{
				trace(error.message);
			}
		}
		
		override public function set visible(value:Boolean):void
		{
			super.visible = value;
			callJS(methodVisibility, value);
		}
		
		override public function set width(value:Number):void
		{
			super.width = value;
			callJS(methodResize, value, height);
		}
		
		override public function set height(value:Number):void
		{
			super.height = value;
			callJS(methodResize, width, value);
		}
		
		override public function set x(value:Number):void
		{
			super.x = value;
			var point:Point = localToGlobal(new Point(0, 0));
			callJS(methodMove, point.x, point.y);
		}
		
		override public function set y(value:Number):void
		{
			super.y = value;
			var point:Point = localToGlobal(new Point(0, 0));
			callJS(methodMove, point.x, point.y);
		}
	}
}

/*
var FlexIFrame = {
get: function(id)
{
var iframe = document.getElementById(id);
if(!iframe)
return FlexIFrame.create(id);
return iframe;
},
create: function(id)
{
var iframe = document.createElement('iframe');
iframe.id = id;
iframe.frameborder = 0;
iframe.style.position = "absolute";
iframe.style.zIndex = 1;
iframe.style.border = "none";
document.body.insertBefore(iframe, document.body.firstChild);
return iframe;
},
resize: function(id, width, height)
{
var iframe = FlexIFrame.get(id);
iframe.style.width = width + "px";
iframe.style.height = height + "px";
},
move: function(id, x, y)
{
var iframe = FlexIFrame.get(id);
iframe.style.left = x + "px";
iframe.style.top = y + "px";
},
navigate: function(id, url)
{
var iframe = FlexIFrame.get(id);
iframe.src = url;
},
visibility: function(id, visible)
{
var iframe = FlexIFrame.get(id);
iframe.style.display = visible ? "block" : "none";
}
}
*/