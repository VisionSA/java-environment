<%@include file="/inc/tags.jsp"%>
 <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/jscript/typecast.js"></script>
 <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/jscript/typecast.config.js"></script>
<f:view>
	<html>
	<head>
	<title><h:outputText value="Tarjeta Fiel - Conciliaciones" /></title>
	<s:script language="javascript">
		
	   // funcion q refrezca en la pagina el resultado de la conciliacion
	   function refrescarResultados(fila){
	       	if(parseInt(fila)< parseInt(document.getElementById('altManualCuponesForm:cantCupones').value)){
	       	      //aca preguntamos (en un elemento cualquiera de la fila siguiente), si esta oculto
	       	      //si ese elemento esta oculto=> toda la fila estara oculta
	       	      //por lo tanto se llamara a la fcon mostrar solo en el caso q este oculta
	       	       document.getElementById('altManualCuponesForm:listado:' + fila + ':cup').focus();
	       	      if(document.getElementById('altManualCuponesForm:listado:' + fila + ':tar').style.display =='none'){ 
	       	        mostrarFila(fila);
 		      	    deshabilitarFila(fila-1);
 		            document.getElementById('altManualCuponesForm:listado:' + fila + ':cup').focus();
 		           } 
 		    }
           	else  
           	       if(!haySinConciliar())
           	        document.getElementById('altManualCuponesForm:showPopup').onclick();
 	    }
	    
	    
	         function haySinConciliar(){
	             for(var i=0; i<parseInt(document.getElementById('altManualCuponesForm:cantCupones').value);i++){
	                if(document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').value!="Trans. no Encontrada"){
	                   return true;
	                }
	                
	             }
	             return false;
	         }
	    

				function esDigito(sChr){
				  var sCod = sChr.charCodeAt(0);
				  return ((sCod > 47) && (sCod < 58));
				}
				
				function valSep(oTxt){
				var bOk = false;
				bOk = bOk || ((oTxt.value.charAt(2) == "-") && (oTxt.value.charAt(5) == "-"));
				bOk = bOk || ((oTxt.value.charAt(2) == "/") && (oTxt.value.charAt(5) == "/"));
				return bOk;
				}
				
				function finMes(oTxt){
				var nMes = parseInt(oTxt.value.substr(3, 2), 10);
				var nRes = 0;
				switch (nMes){
				case 1: nRes = 31; break;
				case 2: nRes = 29; break;
				case 3: nRes = 31; break;
				case 4: nRes = 30; break;
				case 5: nRes = 31; break;
				case 6: nRes = 30; break;
				case 7: nRes = 31; break;
				case 8: nRes = 31; break;
				case 9: nRes = 30; break;
				case 10: nRes = 31; break;
				case 11: nRes = 30; break;
				case 12: nRes = 31; break;
				}
				return nRes;
				}
				
				function valDia(oTxt){
				var bOk = false;
				//var mydate=new Date();
                //var diaActual=	mydate.getDate();
				var nDia = parseInt(oTxt.value.substr(0, 2), 10);
				bOk = bOk || ((nDia >= 1) && (nDia <= finMes(oTxt)));
				//bOk = bOk || ((nDia >= 1) && (nDia <= diaActual));
				return bOk;
				}
				
				function valMes(oTxt){
				var bOk = false;
				//var mydate=new Date();
                //var mesActual =mydate.getMonth() +1;
				var nMes = parseInt(oTxt.value.substr(3, 2), 10);
				//bOk = bOk || ((nMes >= 1) && (nMes <= mesActual));
				bOk = bOk || ((nMes >= 1) && (nMes <= 12));
				return bOk;
				}
				
				function valAno(oTxt){
				var mydate=new Date();
                var year=mydate.getYear();
                if (year < 1000)
	            	year+=1900;
                var bOk = true;
				var nAno = oTxt.value.substr(6);
				bOk = bOk && ((nAno>1980) && (nAno<= year) && (nAno.length == 4));
				if (bOk){
				for (var i = 0; i < nAno.length; i++){
				bOk = bOk && esDigito(nAno.charAt(i));
				}
				}
				return bOk;
				}
				
			function valFecha(oTxt){
				var bOk = true;
				if (oTxt.value != ""){
				bOk = bOk && (valAno(oTxt));
				bOk = bOk && (valMes(oTxt));
				bOk = bOk && (valDia(oTxt));
				bOk = bOk && (valSep(oTxt));
				
				if (!bOk){
		          	 document.getElementById(oTxt.id).style.background = 'red';	 
		             oTxt.focus();
				}else{
				     var mydate=new Date();
                     var diaActual=	mydate.getDate();
                     var mesActual =mydate.getMonth() +1;
                     var anoActual=mydate.getYear();
                     if (anoActual < 1000)
	            	    anoActual+=1900;
				     var nDia = parseInt(oTxt.value.substr(0, 2), 10); 
				     var nMes = parseInt(oTxt.value.substr(3, 2), 10);
				     var nAno = oTxt.value.substr(6);
				     var Fecha = new Date(nAno,nMes,nDia)
                     var FechaActual = new Date(anoActual,mesActual,diaActual)
                     if(Fecha <= FechaActual)
				       document.getElementById(oTxt.id).style.background = 'white';	
				     else {
				          document.getElementById(oTxt.id).style.background = 'red';	 
		                  oTxt.focus();
				     }  
				  }
				}
				}
	      
	    
	    function funcioncita(i){
	       var fila=i-1;
	       document.getElementById('altManualCuponesForm:listado:' + fila+ ':txt').disabled=true;
	    }
	     
		function mask(str,textbox,loc,delim){
            var locs = loc.split(',');
            var cont=0;
               for (var j = 0; j <= str.length; j++){
					  if (str.substring(j, j+1) == delim){
					    cont++;  
					  }
			    }
			    
              if(cont<2){
				for (var i = 0; i <= locs.length; i++){
					for (var k = 0; k <= str.length; k++){
					 if (k == locs[i]){
					  if (str.substring(k, k+1) != delim){
					    str = str.substring(0,k) + delim + str.substring(k,str.length)
					  }
					 }
					}
				 }
				textbox.value = str
			}	
		  }
		
		
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		
	</s:script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/basic.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/popup.css" />
	</head>
	<%@include file="/inc/head.inc"%>

	<body onload="if('${ConciliacionCuponesBean.popup.mostrar}'=='true') {viewDialog.show();} ;"  
		onbeforeunload="ShowWait('altManualCuponesForm');">


	<script languaje="javascript">
        
         var cuponRepetido=false; 
         
        
  		 		 //Permite que se ingrese un número decimal de decimales definidos en un Input Text
				function soloDecimalesPrecisos(InputText, evt, cantDeci) {
					var charCode = (evt.which) ? evt.which : event.keyCode;
					var strValue = '';
					var canti = parseInt(cantDeci);
				 	var selectionStart = getSelectionStart(InputText);
				  	var selectionEnd = getSelectionEnd(InputText);
					strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
					if (!isDecimalConNDecimales(strValue, canti) && charCode > 31) {
						return false;
					}		
					return true;
				}
				
						// Retorna true si inputVal es decimal.
				function isDecimalConNDecimales(inputVal, numeroDecimales) {
					oneDecimal = false;
					cantidades = 0;
					inputStr = inputVal.toString();
					for (var i = 0; i < inputStr.length; i++) {
						var oneChar = inputStr.charAt(i);
						if (i == 0 && oneChar == "-") {
							continue;
						}
						if (oneDecimal) {
						    cantidades = cantidades + 1;
						    if (cantidades > parseInt(numeroDecimales)) {return false;}
						}
						if (oneChar == "." && !oneDecimal) {
							oneDecimal = true;
							continue;
						}
						if (oneChar < "0" || oneChar > "9") {
							return false;
						}
					}
					return true;
				}	 

		function captarEnter(InputText, elEvento) {
		    var evento = window.event || elEvento;
		     return true;
		}
			
	  		
				
	     function captarEnterACodComercio(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	if (InputText.value==""||InputText.value == null) {
		    	   return false;
		    	} else {
		    	   document.getElementById('altManualCuponesForm:com').select();
		    	   document.getElementById('altManualCuponesForm:com').focus();
 		 	       return false;
		    	}

 		 	}
		    return true;
		  }

       
				
		 function captarEnterACantidadCupones(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	if (InputText.value==""||InputText.value == null) {
		    	   return false;
		    	} else {
		    	   document.getElementById('altManualCuponesForm:cantCuponesAlta').select();
		    	   document.getElementById('altManualCuponesForm:cantCuponesAlta').focus();
 		 	       return false;
		    	}

 		 	}
		    return true;
		}		
				
		
		function captarEnterPrincipal(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	   mostrarFila(0);
		    	   document.getElementById('altManualCuponesForm:listado:0:cup').focus();
 		 	       return true;
		    	}
 		}		
       
       function captarEnterANroDoc(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		       	cadena = cadena + "doc";
		        document.getElementById(cadena).focus();
 		 	    return false;
 		 	}	
 		}	
         
				
	   function captarEnterANroTarjeta(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
		    	var aux; 
 		 	    cadena = cadena.substring(0,cadena.length - 3);
 		 	    if (InputText.value!=null && InputText.value!="" ) {
		            //aux= cadena+"cup"; 
		           // validarCupones(document.getElementById(aux).value,InputText.id);
 		 	    	cadena = cadena + "tar";
 		 	    	document.getElementById(cadena).focus();
					return false;
 		 	    }
 		 	    
 		 	}	
 		}
 		
 		
 		function validarTarj1(InputText){
 		           var cadena = InputText.id;
		           var cadena2;
 		           var cadena3;
 		           cadena = cadena.substring(0,cadena.length - 3);
		           cadena2 = cadena + "ta2";
		           cadena3 = cadena + "ta3";
		           if(parseInt(InputText.value.length)!=2){
		              document.getElementById(InputText.id).style.background = 'red';
                      document.getElementById(cadena2).style.background = 'red';
                      document.getElementById(cadena3).style.background = 'red';
                      document.getElementById(InputText.id).focus();
		          }
		         if(parseInt(InputText.value.length)==2 && parseInt(document.getElementById(cadena2).value.length)==4 && parseInt(document.getElementById(cadena3).value.length)==4  ){
			         	cadena = cadena + "cuo";
			         	document.getElementById(cadena).focus();
 		                document.getElementById(InputText.id).style.background = 'white';
 		                document.getElementById(cadena2).style.background = 'white';
 		                document.getElementById(cadena3).style.background = 'white';
 		           }
 		  }
 		
 		
 		
 		
 		 function captarEnterANroTarjeta2(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    
		    
		    if (evento.keyCode == 13) {
		        var cadena = InputText.id;
		            cadena = cadena.substring(0,cadena.length - 3);
			       	cadena = cadena + "ta2";
			       //	document.getElementById(InputText.id).style.background = 'white';
					document.getElementById(cadena).focus();
					return false;
				}
				  	
 		 	}	
 		
 			
 		function validarTarj2(InputText){
 		          var cadena = InputText.id;
		          var cadena1;
 		          var cadena3;
 		          cadena = cadena.substring(0,cadena.length - 3);
		          cadena1 = cadena + "tar";
		          cadena3 = cadena + "ta3";
		          if(parseInt(InputText.value.length)!=4){
		              document.getElementById(InputText.id).style.background = 'red';
                      document.getElementById(cadena1).style.background = 'red';
                      document.getElementById(cadena3).style.background = 'red';
                      document.getElementById(InputText.id).focus();
		          }
 		          if(parseInt(InputText.value.length)==4 && parseInt(document.getElementById(cadena1).value.length)!=1 && parseInt(document.getElementById(cadena3).value.length)==4  ){
			         	cadena = cadena + "cuo";
			         	document.getElementById(cadena).focus();
 		                document.getElementById(InputText.id).style.background = 'white';
 		                document.getElementById(cadena1).style.background = 'white';
 		                document.getElementById(cadena3).style.background = 'white';
 		           }
 		}
 		
 		
 		function validarTarj3(InputText){
 		            var cadena = InputText.id;
		            var cadena1;
 		            var cadena2;
 		            cadena = cadena.substring(0,cadena.length - 3);
		            cadena1 = cadena + "tar";
		            cadena2 = cadena + "ta2";
			       	if(parseInt(InputText.value.length)==4 && parseInt(document.getElementById(cadena1).value.length)!=1 && parseInt(document.getElementById(cadena2).value.length)==4  ){
			         	cadena = cadena + "cuo";
			         	document.getElementById(cadena).focus();
 		                document.getElementById(InputText.id).style.background = 'white';
 		                document.getElementById(cadena1).style.background = 'white';
 		                document.getElementById(cadena2).style.background = 'white';
 		             }else{
                            document.getElementById(InputText.id).style.background = 'red';
                            document.getElementById(cadena1).style.background = 'red';
                            document.getElementById(cadena2).style.background = 'red';
                            document.getElementById(InputText.id).focus();
                             		             
 		             }  
 		
 		}
 		 function captarEnterANroTarjeta3(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		       	cadena = cadena + "ta3";
		       	document.getElementById(cadena).focus();
 		 	    return false;
 		 	}	
 		}	
 		
 		
 		
			
		 function captarEnterACantCuotas(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
		    	var i;
          	    i = cadena.substring(29,cadena.length - 4);
          	
 		     	if( document.getElementById('altManualCuponesForm:listado:' + i + ':tar').value==""
		     	    && document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').value=="" 
		     	    && document.getElementById('altManualCuponesForm:listado:' + i + ':ta3').value==""){
		     	         document.getElementById('altManualCuponesForm:listado:' + i + ':tar').value="00";
		     	         document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').value="0000";
		     	         document.getElementById('altManualCuponesForm:listado:' + i + ':ta3').value="0000";
		     	    }
		    	    cadena = cadena.substring(0,cadena.length - 3);
		        	cadena = cadena + "cuo";
		        	document.getElementById(cadena).focus();
		    	
 		 	    
 		 	    return false;
 		 	}	
 		}	
	    		
		
		 function captarEnterAImporte(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "imp";
 		 	    	document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
		
		function captarEnterAImporteSinDesc(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "aut";
 		 	    	document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
		
		function captarEnterACodAutorizacion(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "aut";
 		 	    	document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
 		
 		
 		function conciliarDesdeIcono(i){
 		
 		    var fila=i-1;
 		    // valda vacios
	       
 		        if ( document.getElementById('altManualCuponesForm:listado:' + fila+ ':cup').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':doc').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':tar').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + + fila+ ':ta2').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':ta3').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':cuo').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':imp').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':aut').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':fec').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + fila+ ':pln').value==""  ){
		            return false;
		        }
 		        //valida cupones repetidos
		    	
          	    var color= document.getElementById('altManualCuponesForm:listado:' + fila + ':cup').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	       //valida codigos de aut repetidos
		        color= document.getElementById('altManualCuponesForm:listado:' + fila + ':aut').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	     //valida formato tarjeta
		        color= document.getElementById('altManualCuponesForm:listado:' + fila + ':tar').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	     //valida formato fecha
		        color= document.getElementById('altManualCuponesForm:listado:' + fila + ':fec').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
 		 	    // llamada al server mediante ajax solo si paso las validaciones anteriores
 		 	    
		        if (document.getElementById('altManualCuponesForm:listado:' + fila+ ':cup').value!="") {
		            
 		 	      	document.getElementById('altManualCuponesForm:listado:' + fila+ ':txt').disabled=false;
 		 	     	document.getElementById('altManualCuponesForm:listado:' + fila+ ':txt').focus();
 		 	    }
 		 	    return false;
 		
 		
 		}
 		
 		
 		
 		 function captarEnterAtxtResul(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
 		 	    var cadenaDos;
 		 	    var cadenaTres;
 		 	    var cadenaCuatro;
 		 	    cadenaDos = cadena + "cup";
 		 	    cadenaTres = cadena + "tar";
 		 	    cadenaCuatro = cadena + "cuo";
 		 	    
 		 	    cadena = InputText.id;
		    	var cadenaAux;
          	    cadenaAux = cadena.substring(29,cadena.length - 4);
 		 	    
 		 	    ///valida campos vacios
 		 	    // if (document.getElementById(cadenaDos).value==""||( document.getElementById(cadenaTres).value=="" && document.getElementById(cadenaCuatro).value=="")) {
		          
		          if ( document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':cup').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':doc').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':tar').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':ta2').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':ta3').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':cuo').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':imp').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':aut').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':fec').value==""  ||
		               document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':pln').value==""  ){
		          
		            return false;
		        }
		        //valida cupones repetidos
		    	
          	    var color= document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':cup').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	     //valida codigos de aut repetidos
		        color= document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':aut').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	     //valida formato tarjeta
		        color= document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':tar').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
          	     //valida formato fecha
		        color= document.getElementById('altManualCuponesForm:listado:' + parseInt(cadenaAux) + ':fec').style.background;
          	    if(color.substring(0,3)=="red"){
          	       return false;
          	    }
          	    
        	    // llamada al server mediante ajax solo si paso las validaciones anteriores
 		 	    
		        if (InputText.value!=""&&InputText.value!=null) {
		            var id = InputText.id;
  		 	        id = id.substring(0,cadena.length - 3);
 		 	    	id = id + "txt";
 		 	      	document.getElementById(id).disabled=false;
 		 	     	document.getElementById(id).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
 		
 		
 		function captarEnterAFechaConsumo(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "fec";
 		 	    	document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
 		
 		
 		
 		function captarEnterAPlanCuota(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "pln";
 		 	    	document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}	
 		}
 		
 		
			
		
			
		 function validarCodAut(InputText){
	       validarCodigosAut(document.getElementById(InputText.id).value,InputText.id);
	    }	
		
			
			
		 function validarCodigosAut(codAut,filaActual){
		   var i;
		   codigoRepetido= false;
		 /*  var cuponActual='';
		  for(i=0;i<(4-nroCupon.length);i++){
		    cuponActual+='0';
	      }
	      cuponActual=cuponActual+nroCupon;*/
	      var   cuponesCargados= document.getElementById('altManualCuponesForm:cantCuponesCargados').value;
	      var inicial= document.getElementById('altManualCuponesForm:inicial').value;
	      
	      var hasta = parseInt(cuponesCargados)+parseInt(inicial);
	      
          for (i = 0; i < hasta; i++) {
	           if('altManualCuponesForm:listado:' + i + ':aut' != filaActual){
		          
		           if(codAut ==  document.getElementById('altManualCuponesForm:listado:' + i + ':aut').value){
		              codigoRepetido =true;
		              break;
		             } 
		       }
          }
     	    var y= filaActual.substring(29,filaActual.length);
            filaActual= y.substring(0,y.indexOf(":"));
      
          if(!codigoRepetido)  {
                document.getElementById('altManualCuponesForm:listado:' +filaActual + ':aut').style.background = 'white';
                document.getElementById('altManualCuponesForm:listado:' +filaActual + ':fec').focus();
           } else {
  		         document.getElementById('altManualCuponesForm:listado:' +filaActual + ':aut').style.background = 'red';
  		         document.getElementById('altManualCuponesForm:listado:' +filaActual + ':aut').focus();
           }
  		 }
		 		
	    	
			
			
	    function validarCupon(InputText){
	       validarCupones(document.getElementById(InputText.id).value,InputText.id);
	    }		
	    
	   
			
		function validarCupones(nroCupon,filaActual){
		   var i;
		   cuponRepetido= false;
		 /*  var cuponActual='';
		  for(i=0;i<(4-nroCupon.length);i++){
		    cuponActual+='0';
	      }
	      cuponActual=cuponActual+nroCupon;*/
	      var   cuponesCargados= document.getElementById('altManualCuponesForm:cantCuponesCargados').value;
	      var inicial= document.getElementById('altManualCuponesForm:inicial').value;
	      
	      var hasta = parseInt(cuponesCargados)+parseInt(inicial);
	      
          for (i = 0; i < hasta; i++) {
	           if('altManualCuponesForm:listado:' + i + ':cup' != filaActual){
		          
		           if(nroCupon ==  document.getElementById('altManualCuponesForm:listado:' + i + ':cup').value){
		              cuponRepetido =true;
		              break;
		             } 
		       }
          }
     	    var y= filaActual.substring(29,filaActual.length);
            filaActual= y.substring(0,y.indexOf(":"));
      
          if(!cuponRepetido)  {
                document.getElementById('altManualCuponesForm:listado:' +filaActual + ':cup').style.background = 'white';
                document.getElementById('altManualCuponesForm:listado:' +filaActual + ':doc').focus();
           } else {
  		         document.getElementById('altManualCuponesForm:listado:' +filaActual + ':cup').style.background = 'red';
  		         document.getElementById('altManualCuponesForm:listado:' +filaActual + ':cup').focus();
           }
  		 }
		 		
        
		function deshabilitarFila(i){
		  /*  document.getElementById('altManualCuponesForm:listado:' + i + ':cup').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':imp').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':aut').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':pln').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':tip').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':res').readOnly = true;*/
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cup').disabled = true;
            document.getElementById('altManualCuponesForm:listado:' + i + ':tar').disabled = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').disabled = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':imp').disabled = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':aut').disabled = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':pln').disabled = true;
		    //document.getElementById('altManualCuponesForm:listado:' + i + ':tip').disabled = true;
		    //document.getElementById('altManualCuponesForm:listado:' + i + ':res').disabled = true;
		    return null;
		}
		
		
	    function habilitarFila(i){
		    alert("llamada hab fila");
		  //document.getElementById('altManualCuponesForm:listado:' + i + ':cup').readOnly = true;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cup').disabled = false;
            document.getElementById('altManualCuponesForm:listado:' + i + ':tar').disabled = false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').disabled = false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':imp').disabled = false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':aut').disabled = false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':pln').disabled = false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':pte').value = true;
		    return null;
		
		}	
		
		
				
		
		function mostrarFila(i) {
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cup').disabled= false;
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cup').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':doc').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':fij').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':tar').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':ta3').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':imp').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':aut').style.display = '';
	        document.getElementById('altManualCuponesForm:listado:' + i + ':fec').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':pln').style.display = '';
		    document.getElementById('altManualCuponesForm:listado:' + i + ':fil').style.display = '';
	     	document.getElementById('altManualCuponesForm:listado:' + i + ':txt').style.display = '';
	     	//document.getElementById('altManualCuponesForm:listado:' + i + ':cup').focus();
		    return null;
		}
		
		
		function ocultarFila(i){
		            document.getElementById('altManualCuponesForm:listado:' + i + ':cup').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':doc').style.display = 'none';
                  	document.getElementById('altManualCuponesForm:listado:' + i + ':fij').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':tar').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':ta3').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':imp').style.display = 'none';
			      //  document.getElementById('altManualCuponesForm:listado:' + i + ':isd').style.display = 'none';
			        document.getElementById('altManualCuponesForm:listado:' + i + ':aut').style.display = 'none';
			       document.getElementById('altManualCuponesForm:listado:' + i + ':fec').style.display = 'none';
			        document.getElementById('altManualCuponesForm:listado:' + i + ':pln').style.display = 'none';
			     //	document.getElementById('altManualCuponesForm:listado:' + i + ':tip').style.display = 'none';
			     //	document.getElementById('altManualCuponesForm:listado:' + i + ':res').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':fil').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resI').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resJ').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resK').style.display = 'none';
		
		
		}
		
		
		
	function ocultarFila() {
	
	 if( parseInt(document.getElementById('altManualCuponesForm:cantCuponesCargados').value)+1 < parseInt(document.getElementById('altManualCuponesForm:cantCupones').value)){
		
		    var inicial;
		     //  este es valor inicial q indica desde q nro fila tengo que ocultar(empieza desde cero)
             // si  estoy en el alta inicial vale 1(segunda fila)
             // si  estoy en edicion  inicial = cantidad de registros cargados en la bd, para el lote seleccionado
            inicial=parseInt(document.getElementById('altManualCuponesForm:inicial').value);
	        
	        var cuponesEnMemoria; 
	        cuponesEnMemoria=parseInt(document.getElementById('altManualCuponesForm:cantCuponesCargados').value)+1;
	       
	        //aca pone los graficos del resultado conciliacion el el caso de que no haya entrado por alta nvo lote
		   if( document.getElementById('altManualCuponesForm:mostrarFiltro').value== "false" ){
		       for(var i=0;i<cuponesEnMemoria;i++){
		          setearResultadoConciliacion(i,document.getElementById('altManualCuponesForm:listado:' + i + ':txt').value);   
		       } 
		   }
		   
		  
		   
		   
		   
		   
		  
		   //else mostrarFila(0);
		     
		    for (var i = inicial+cuponesEnMemoria-1; document.getElementById('altManualCuponesForm:listado:' + i + ':cup')!=null &&  document.getElementById('altManualCuponesForm:listado:' + i + ':cup').value=="" ; i++) {
		           if (document.getElementById('altManualCuponesForm:listado:' + i + ':cup').value==null || document.getElementById('altManualCuponesForm:listado:' + i + ':cup').value=="") {
                	document.getElementById('altManualCuponesForm:listado:' + i + ':cup').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':doc').style.display = 'none';
                  	document.getElementById('altManualCuponesForm:listado:' + i + ':fij').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':tar').style.display = 'none';
                	document.getElementById('altManualCuponesForm:listado:' + i + ':ta2').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':ta3').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':cuo').style.display = 'none';
			    	document.getElementById('altManualCuponesForm:listado:' + i + ':imp').style.display = 'none';
			      //  document.getElementById('altManualCuponesForm:listado:' + i + ':isd').style.display = 'none';
			        document.getElementById('altManualCuponesForm:listado:' + i + ':aut').style.display = 'none';
			       document.getElementById('altManualCuponesForm:listado:' + i + ':fec').style.display = 'none';
			        document.getElementById('altManualCuponesForm:listado:' + i + ':pln').style.display = 'none';
			     //	document.getElementById('altManualCuponesForm:listado:' + i + ':tip').style.display = 'none';
			     //	document.getElementById('altManualCuponesForm:listado:' + i + ':res').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':fil').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':txt').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resI').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resJ').style.display = 'none';
			     	document.getElementById('altManualCuponesForm:listado:' + i + ':resK').style.display = 'none';
			     	
			        }
               	}
               	
              
             } 
             else { // si llegue al final de cupones a cargar 
                      for(var i=0;i<parseInt(document.getElementById('altManualCuponesForm:cantCupones').value);i++) 
                        setearResultadoConciliacion(i,document.getElementById('altManualCuponesForm:listado:' + i + ':txt').value);
			     
                  }
             
           }  			
		
		
		
		
		
		
		
		
		
		
			
		
		
				
				
			
				
    </script>





	<center><h:form id="altManualCuponesForm">

		<x:panelTabbedPane bgcolor="#dcdcdc">
          <x:div  style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 1100px; HEIGHT: 550px; border: 1px; margin-left: auto; margin-right: auto; background-color: white;">
			<h:panelGroup id="altaAsientos">
				<h:outputText value="Alta de Lotes  Cupones Comercio"
					style="FONT-SIZE: large;" styleClass="texto" />
				<%-- INCLUDE PARA LOS ERRORES --%>
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog" 
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{ConciliacionCuponesBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{ConciliacionCuponesBean.popup.nombreIcono}" />
							<h:outputText value="#{ConciliacionCuponesBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="2" width="286"  align="center" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarBotonesPopupGuardar}">
							<x:commandButton action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarAFiltroDesdePopup}" 
								onclick="clickLink('buscarLote');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo Lote" styleClass="botones" title="Cargar Nvo lote."/>
							<x:commandButton action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarANvoDesdePopup}" 
								onclick="clickLink('nuevoLote');dojo.widget.byId('viewDialog').hide();"
								value="Buscar Lotes Pendientes" styleClass="botones" title="Buscar Lotes."/>
            			</h:panelGrid>
						<h:panelGrid columns="2" width="120" align="center" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarBotonesPopupConfirma2}">
							<x:commandButton action="#{ConciliacionCuponesBean.popupAltaLoteComercio.ejecutarAccion}" 
								onclick="clickLink('ejecutarAccion');dojo.widget.byId('viewDialog').hide();"
								value="Si" styleClass="botones" title="Realiza la accion solicitada."/>
							<x:commandButton action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cancelarEjecutarAccion}" 
								onclick="clickLink('cancelarEjecutar');dojo.widget.byId('viewDialog').hide();"
								value="No" styleClass="botones" title="Cancela la accion solicitada."/>
						</h:panelGrid>
						
						
				 </s:modalDialog>
				  <x:commandLink id="nuevoLote" action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarANvoDesdePopup}" forceId="true" style="display: block;"/>
				  <x:commandLink id="buscarLote" action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarAFiltroDesdePopup}" forceId="true" style="display: block;"/>
            	  <x:commandLink id="ejecutarAccion" action="#{ConciliacionCuponesBean.popupAltaLoteComercio.ejecutarAccion}" forceId="true" style="display: block;"/>
				  <x:commandLink id="cancelarEjecutar" action="#{ConciliacionCuponesBean.popupAltaLoteComercio.cancelarEjecutarAccion}" forceId="true" style="display: block;"/>	
				  
			    
			    <h:inputHidden id="mostrarFiltro" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro}" />
                <h:inputHidden id="cantCuponesCargados" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCuponesCargados}" />
                <h:inputHidden id="actualizarCantFilas"	value="#{ConciliacionCuponesBean.popupAltaLoteComercio.actualizarCantFilas}" />
                <h:inputHidden id="cantCupones"	value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCupones}" />
                <h:inputHidden id="inicial"	value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCuponesCargadosBD}" />
                
                
                 
				<h:panelGrid columns="1" id="genera" width="800">
					<h:panelGrid columns="1" id="opcionbotones">
						<c:if
							test="${ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro}">
							<x:commandLink value="" title="Busca Lotes no cerrados"
								actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarANvo}"
								id="load3">
								<x:graphicImage id="imgn3" value="/img/lotesPendientes.png"
									style=" width : 210px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
						<c:if
							test="${!ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro}">
							<x:commandLink value="" title="Carga un  Nvo Lote"
								actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarAFiltro}"
								id="load2">
								<x:graphicImage id="imgn2" value="/img/nvaCarga.png"
									style=" width : 210px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
					</h:panelGrid>
					<h:panelGrid columns="6" id="datosACargar" width="900" align="left" 
						rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro}">
						<h:outputText value="Fecha Recepcion:" style="width: 250px;" />
						<x:inputCalendar id="fechaRecepcion"     disabled="true"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"
							onkeydown=" captarEnterACodComercio(this,event);" forceId="true" />

						<h:outputText value="Cod Comercio:" style="width: 250px;" />
						<h:inputText   id="com"  disabled="#{ConciliacionCuponesBean.popupAltaLoteComercio.disabledcodComText}"  
 							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codComercio}"
							style="width: 120px; display:hidden" >
					    </h:inputText>
                        <h:outputText value="Importe:" />
                        <h:inputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.importe}"  onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
                        <h:outputText value="Nro de Lote:" style="width: 250px;" />
						<h:inputText
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.nroLoteComercio}"
							style="width: 120px; display:hidden" id="nrolotComer"
							onkeydown="captarEnterPrincipal(this.value, event);"
							onkeypress="return soloEnteros(this,event);" maxlength="11"/>
							
						<h:outputText value="Cantidad Cupones:" style="width: 250px;" />
						<h:inputText
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCupones}"
							style="width: 120px; display:hidden" id="cantCuponesAlta"
							onkeydown="captarEnterPrincipal(this.value, event);"
							onkeypress="return soloEnteros(this,event);" maxlength="3"/>
						<x:commandLink value="" title="Habilitar Carga"
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.cargar}"
							id="load">
							<x:graphicImage id="imgn" value="/img/spreadsheet2.png"
								style=" width : 32px;"></x:graphicImage>
							<f:param name="cantCuponesPreviaModificacion" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCuponesPreviaModificacion}"/>		
						</x:commandLink>
						
						<s:fieldset   legend="Datos del comercio"  id ="legSuc" style="width:200px;background-color:#B9C7D2;height:100px;" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarSucEmpresa}">
                          <h:panelGrid id="datosSucurSeleccionada" columns="2" align="center">
                                <h:outputText value="Sucursal: "  style="color:#D3D3D3;"/>
                                <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.descripcion}" id="outD" style="color:#D3D3D3;"/>
                                <h:outputText value="Direccion:  "  styleClass="texto"/>
                                <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.calleNombre} #{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.calleNumero}"  id="outDireccion" styleClass="textoblue"/>
								<h:outputText value="Barrio: "  styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.barrio.descripcion}" id="outbarri" styleClass="textoblue"/>
								<h:outputText value="Localidad: " id="outLoca" styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.localidad.nombre}" id="outloca" styleClass="textoblue"/>
								<h:outputText value="Teléfono: " id="outT" styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.telefonoSucursal}" id="outTelefono" styleClass="textoblue"/>
							</h:panelGrid>        
						</s:fieldset>
						
                         <h:panelGroup>
								<h:outputText id="outPrint"     rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarSucEmpresa}" value="Impresora:" styleClass="texto"/>
								<h:selectOneMenu id="lstImpresoras" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.idImpresoraSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"  rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarSucEmpresa}"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems value="#{ConciliacionCuponesBean.popupAltaLoteComercio.impresorasItem}"/>
								</h:selectOneMenu>
						</h:panelGroup>
					</h:panelGrid>
                   <h:panelGrid columns="1" bgcolor="FFFFFF" id="pnlContenedBusq">
                  	<s:fieldset legend="Busqueda Lotes No Cerrados"  id="flsetBuscar" rendered="#{!ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro}">
	               
					<h:panelGrid columns="5" id="filtrosBusqueda" width="700"	align="left" >
						<h:outputText value="Cod Comercio:" style="width: 250px;" />
						<h:inputText
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codComercioFiltro}"
							style="width: 120px; display:hidden" id="codComFiltrp" 
							onkeypress="return soloEnteros(this,event);">
						</h:inputText>	
						<h:outputText value="Nro Lote:" rendered="false" style="width: 250px;" />
                    	 <h:inputText   rendered="false"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.nroLoteFiltro}"
							maxlength="11"
							style="width: 120px; display:hidden" id="nroLoteFiltro"
							onchange="rellenarNro(this, this.value,11);"
							onkeypress="return soloEnteros(this,event);" />
                         <h:outputText value="Operador" styleClass="texto"/>
					   	 <h:selectOneMenu id="lstOperador" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.idOperadorSeleccionado}"
								styleClass="lista" immediate="true" onfocus="encender(this);" binding="#{ConciliacionCuponesBean.popupAltaLoteComercio.operadorHtml}"
								onblur="apagar(this);" style="width: 200px">
								<f:selectItems value="#{ConciliacionCuponesBean.popupAltaLoteComercio.operadorItems}"/>
						 </h:selectOneMenu>	
					 
						<x:commandLink value="" title="Buscar Cupones"
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.buscarLotesNoCerrados}"
							id="busqueda">
							<x:graphicImage id="imgnBusqueda" value="/img/icon/srch_32.gif"
								style=" width : 32px;"></x:graphicImage>
						</x:commandLink>

					</h:panelGrid>
					<h:panelGrid columns="1">
						
						<c:if test="${ConciliacionCuponesBean.busquedaAvanzada}">
							<x:commandLink value="" title="Ocultar Busqueda Avanzada" 	id="OcultarAvanz"
									actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.ocultarAvanzada}">
								<x:graphicImage id="ocultAvanzImg" value="/img/arrow_u_32.png"
									style="width : 18px; height : 18px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
						<c:if test="${!ConciliacionCuponesBean.busquedaAvanzada}">
							<x:commandLink value="" title="Mostrar  Busqueda Avanzada" id="buscAvanz"
								actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarAvanzada}">
								<x:graphicImage id="buscAvanzImg" value="/img/arrow_d_32.png" style="width : 18px; height : 18px;"></x:graphicImage>
							</x:commandLink>
						</c:if>
					</h:panelGrid>
					<h:panelGrid columns="4" id="filtrosBusquedaAvanzada" width="700"
						align="left" rendered="#{ConciliacionCuponesBean.busquedaAvanzada}">
						
						<h:outputText value="Fecha Recepcion Desde:" style="width: 250px;" />
						<x:inputCalendar id="fecLoteFiltroRecDesde"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
						<h:outputText value="Fecha Recepcion Hasta:" style="width: 250px;" />
						<x:inputCalendar id="fecLoteFiltroRecHasta"
							monthYearRowClass="yearMonthHeader" weekRowClass="weekHeader"
							popupButtonStyleClass="standard_bold"
							currentDayCellClass="currentDayCell"
							value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}"
							renderAsPopup="true" styleClass="bordeceldainferior"
							style="width: 90px" popupDateFormat="dd/MM/yyyy"
							popupWeekString="#{example_messages['popup_week_string']}"
							helpText="DD/MM/YYYY"
							onkeydown="captarEnterACantidadCupones(this,event);"
							forceId="true" />
					</h:panelGrid>
                    

					<h:panelGrid columns="1" width="650" align="center"	id="panelListadoPendientes" >
						<c:if		test="${empty ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}">
							<h:outputText value="La Busqueda no produjo resultados."
								styleClass="texto" style="color:green" />
						</c:if>


						<c:if 			test="${!empty ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}">
							<h:dataTable align="center"
								value="#{ConciliacionCuponesBean.popupAltaLoteComercio.listaLotesAbiertos}"
								id="tablaNoConciliados" styleClass="standardTable"
								headerClass="dataTable_Header"
								footerClass="standardTable_Header"
								rowClasses="standardTable_Row1,standardTable_Row2"
								columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
								var="obj" style=" width : 570px;">



								<h:column>
									<f:facet name="header">
										<h:outputText value="Id Lote" styleClass="texto"
											style="font: bold;color: white;" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.idLoteComercio}"
										style=" width : 150px;" styleClass="textoblue" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Fecha Lote" styleClass="texto"
											style="font: bold;color: white;" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.fechaRecepcion}"
										style=" width : 150px;" styleClass="textoblue" />
								</h:column>

								<h:column>
									<f:facet name="header">
										<h:outputText value="Cod Comercio" styleClass="texto"
											style="font: bold;color: white;" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.codComercio}"
										style=" width : 150px;" styleClass="textoblue" />

								</h:column>


								<h:column>
									<f:facet name="header">
										<h:outputText value="Cant cupones" styleClass="texto"
											style="font: bold;color: white;" />
									</f:facet>
									<h:outputText value="#{obj.loteComercioResumen.cantCupones}"
										style=" width : 150px;" styleClass="textoblue" />

								</h:column>



								<h:column>
								<x:commandLink value="" title="Agregar items al lote" 	id="agregarItems"
									actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.agregarItemsALote}">
								<x:graphicImage id="agregarItemsImg" value="/img/table_add.png"  
									style="width : 18px; height : 18px;"></x:graphicImage>
								  <f:param name="idLote" value="#{obj.loteComercioResumen.idLoteComercio}"/>
								  <f:param name="comercio" value="#{obj.loteComercioResumen.codComercio}"/>
							      
							      </x:commandLink>
								</h:column>
								
								

							</h:dataTable>
						    <h:panelGrid id="botoneraPaginador2" columns="7" align="center"
								rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.hayLotesAbiertos}">
								<h:commandLink id="botonPrimeraPagina2"
									action="#{ConciliacionCuponesBean.popupAltaLoteComercio.primerRegistro}"
									rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.hayAnterior}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/skipb_16.gif" border="0" />
								</h:commandLink>
								<h:commandLink id="botonPaginaAnterior2"
									action="#{ConciliacionCuponesBean.popupAltaLoteComercio.anteriorRegistro}"
									rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.hayAnterior}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/rewnd_16.gif" border="0" />
								</h:commandLink>
								<h:outputText value="Página " />
								<h:selectOneMenu id="lstDeEjerciciosPorsucu2"
									value="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.idPaginaSeleccionada}"
									binding="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.pagSeleccionada}"
									styleClass="lista" onfocus="encender(this);" immediate="true"
									valueChangeListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.cambiarPagina}"
									onblur="apagar(this);" style=" width : 50px;"
									onchange="submit();">
							    <f:selectItems	value="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.comboDePaginas}"
										id="selectEjerDeSucumA2" />
								</h:selectOneMenu>								<h:outputText
									value="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.estado}" />
								<h:commandLink id="botonPaginaSiguiente2"
									action="#{ConciliacionCuponesBean.popupAltaLoteComercio.siguienteRegistro}"
									rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.haySiguiente}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/fastf_16.gif" border="0" />
								</h:commandLink>
								<h:commandLink id="botonUltimaPagina2"
									action="#{ConciliacionCuponesBean.popupAltaLoteComercio.ultimoRegistro}"
									rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.paginador.haySiguiente}"
									styleClass="botones">
									<x:graphicImage value="/img/icon/skipf_16.gif" border="0" />
								</h:commandLink>
							</h:panelGrid>
						
						</c:if>

					</h:panelGrid>
               </s:fieldset>  
               </h:panelGrid>
				</h:panelGrid>
				
				<h:panelGrid id="cabecera" columns="6" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarCabeceraLoteEdicion}">
			      <h:outputText value="Fecha Recepcion:" />
				  <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.fechaRecepcion}" />
				  <h:outputText value="Cod comercio:" />
				  <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codComercio}">
			     </h:outputText> 
				  <h:outputText value="Nro Lote:" />
				  <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.nroLote}" />
			      <h:outputText value="Cant Cupones:" />
			      <h:panelGrid columns="4">
			  
					    <h:inputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCupones}" />
					  	<x:commandLink value="" title="Habilitar Carga"
								actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.agregarFilas}"
								id="agregarFilas">
								<x:graphicImage id="agregarFilasImg" value="/img/spreadsheet2.png"
									style=" width : 32px;"></x:graphicImage>
								 <f:param name="cantCuponesPreviaModificacion" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCuponesPreviaModificacion}"/>	
									
						</x:commandLink>
						
						<s:fieldset  id ="legSucEdit" legend="Datos del comercio"  style="width:200px;background-color:#B9C7D2;height:100px;" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarSucEmpresa}">
                          <h:panelGrid id="datosSucurSelEdit" columns="2" align="center">
                                <h:outputText value="Sucursal: "  id="sucEdit" style="color:#D3D3D3;"/>
                                <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.descripcion}" id="outDEdit" style="color:#D3D3D3;"/>
                                <h:outputText value="Direccion:  "  id="dirEdit" styleClass="texto"/>
                                <h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.calleNombre} #{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.calleNumero}"  id="outDireccionEdit" styleClass="textoblue"/>
								<h:outputText value="Barrio: " id="barEdit" styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.barrio.descripcion}" id="outbarriEdit" styleClass="textoblue"/>
								<h:outputText value="Localidad: "  id="locEdit" styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.codCom.sucEmpresa.domicilio.localidad.nombre}" id="outlocaEdit" styleClass="textoblue"/>
								<h:outputText value="Teléfono: " id="telEdit" styleClass="texto"/>
								<h:outputText value="#{ConciliacionCuponesBean.popupAltaLoteComercio.telefonoSucursal}" id="outTelefonoEdit" styleClass="textoblue"/>
							</h:panelGrid>        
						</s:fieldset>
                          <h:panelGroup id="pnlImprEdit">
								<h:outputText id="outimpEdit"   rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarGuardar}"  value="Impresora:" styleClass="texto"/>
								<h:selectOneMenu id="lstImpresorasEdit" value="#{ConciliacionCuponesBean.popupAltaLoteComercio.idImpresoraSeleccionada}"
									styleClass="lista" immediate="true" onfocus="encender(this);"  rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarGuardar}"
									onblur="apagar(this);" style="width: 200px">
									<f:selectItems  id="impItemsID"  value="#{ConciliacionCuponesBean.popupAltaLoteComercio.impresorasItem}"/>
								</h:selectOneMenu>
						</h:panelGroup>
				  </h:panelGrid>	
				</h:panelGrid>
				    <h:inputHidden id="cantCuponesPreviaModificacion"	value="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCuponesPreviaModificacion}" />
              
				
               
				<%-- Comienzan los cambios --%>

				<h:panelGrid id="panelDeLosDetallesdelLote" width="1000">
                   
					<x:inputHidden id="pp" forceId="true"
						value="#{ConciliacionCuponesBean.listaDeModicadosEnString}" />
					<x:inputHidden id="ee" forceId="true"
						value="#{ConciliacionCuponesBean.listaDeEliminadosEnString}" />




					<c:if test="${!empty ConciliacionCuponesBean.loteComercioDetalles}">
                       
						<h:dataTable id="listado" styleClass="standardTable"
							headerClass="dataTable_Header" footerClass="standardTable_Header"
							rows="#{ConciliacionCuponesBean.popupAltaLoteComercio.cantCupones}"
							var="obj"  value="#{ConciliacionCuponesBean.loteComercioDetalles}" style=" width : 1000px;">
							
                            
                            <x:column>
								<f:facet name="header">
									<h:outputText value="N°" id="out1" />
								</f:facet>
								<h:outputText id="fil" styleClass="borderText"
									value="#{obj.fila}"   />
									
									
							</x:column>
                            
                            
							<x:column>
								<f:facet name="header">
									<h:outputText value="Nro Cupon" id="out2" />
								</f:facet>
								
								<h:inputText id="cup" 
									maxlength="4" value="#{obj.loteComercioItem.nroCupon}"
									onkeypress="return soloEnteros(this, event);"
									onkeydown="captarEnterANroDoc(this, event);"
									style="width: 65px; display:hidden" disabled="#{!obj.soyEditable}"
									onblur="validarCupon(this);" >
									<f:converter converterId="quitarPrefijoCero4"/>
								</h:inputText>	
							</x:column>
							
							
							<x:column>
								<f:facet name="header">
									<h:outputText value="Nro Doc " id="out3" />
								</f:facet>
								
								<h:inputText value="#{obj.loteComercioItem.nroDoc}"  
									onkeydown="captarEnterANroTarjeta(this, event);"
									onkeypress="return soloEnteros(this,event);"
									style="width: 65px; display:hidden" id="doc" disabled="#{!obj.soyEditable}"
									onfocus="encender(this);" onblur="apagar(this);" maxlength="8" />
								
							</x:column>
							

							<x:column width="140">
								<f:facet name="header"  >
									<h:outputText value="Numero  Tarjeta"  />
								</f:facet>
								<h:panelGrid columns="4">
							    	<h:outputText  id="fij" value="5049-06" styleClass="borderText" style="width: 40px;" />
							    	<h:inputText value="#{obj.nroTarjeta1}"
								      onkeydown="captarEnterANroTarjeta2(this, event);"
								      onkeypress="return soloEnteros(this,event);"
									  style="width: 20px; display:hidden" id="tar" disabled="#{!obj.soyEditable}"
									  onfocus="encender(this);" onblur="apagar(this);validarTarj1(this);" maxlength="2"/>
									<h:inputText value="#{obj.nroTarjeta2}"
								        onkeydown="captarEnterANroTarjeta3(this, event);" 
								        onkeypress="return soloEnteros(this,event);" disabled="#{!obj.soyEditable}"
								        maxlength="4" style="width: 35px; display:hidden" id="ta2" 
									    onfocus="encender(this);" onblur="apagar(this);validarTarj2(this);"/>
									<h:inputText value="#{obj.nroTarjeta3}" maxlength="4"
								        onkeydown="captarEnterACantCuotas(this, event);"
								        onkeypress="return soloEnteros(this,event);" disabled="#{!obj.soyEditable}"
									    style="width: 35px; display:hidden" id="ta3"
									    onfocus="encender(this);" onblur="apagar(this);validarTarj3(this);"/> 
								</h:panelGrid>	
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Cant Cuotas" />
								</f:facet>
								<h:inputText value="#{obj.loteComercioItem.cantCuotas}"
									onkeydown="captarEnterAImporte(this, event);"
									style="width: 70px; display:hidden" id="cuo" disabled="#{!obj.soyEditable}"
									onkeypress="return soloEnteros(this, event);" maxlength="2"
									onfocus="encender(this);" onblur="apagar(this);" >
									<f:converter converterId="quitarPrefijoCero2"/>
								</h:inputText>	
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Importe" />
								</f:facet>
								<h:inputText value="#{obj.loteComercioItem.importe}"
									onkeydown="captarEnterAImporteSinDesc(this, event);"
									onkeypress="return soloDecimalesPrecisos(this,event,2);"
									id="imp"  maxlength="9" disabled="#{!obj.soyEditable}" 
									style="width: 100px; display:hidden" onfocus="encender(this);"
									onblur="apagar(this);" >
                               </h:inputText>	
							</x:column>





							<x:column>
								<f:facet name="header">
									<h:outputText value="Cod. Autorizacion" />
								</f:facet>
								<h:inputText value="#{obj.loteComercioItem.codigoAutorizacion}"
									onkeydown="captarEnterAFechaConsumo(this, event);"
									onkeypress="return soloEnteros(this,event);"
									id="aut" disabled="#{!obj.soyEditable}"
									maxlength="6"
									style="width: 80px; display:hidden" onfocus="encender(this);"
									onblur="apagar(this);validarCodAut(this);" >
								</h:inputText>	
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Fecha Consumo" />
								</f:facet>
							     <h:inputText value="#{obj.loteComercioItem.fechaReal}"
									onkeydown="captarEnterAPlanCuota(this, event);"  id="fec" 
									maxlength="10" style="width: 80px; display:hidden" onfocus="encender(this);"
									onkeyup="mask(this.value,this,'2,5','/');"  disabled="#{!obj.soyEditable}"
									onblur="apagar(this);valFecha(this);">
									<f:converter converterId="fechaCuponConverter"/>
								</h:inputText>	
							 
							</x:column>


							<x:column>
								<f:facet name="header">
									<h:outputText value="Plan Cuotas" id="tly"
										style="display:hidden" />
								</f:facet>
								<h:inputText value="#{obj.loteComercioItem.planCuotas}" id="pln"
									onkeydown="captarEnterAtxtResul(this, event);" 
									onkeypress="return soloEnteros(this,event);" maxlength="1"
									style="width:100px;display:hidden" disabled="#{!obj.soyEditable}"
									 onfocus="encender(this);">
                               </h:inputText>
							</x:column>

							<x:column>
								<f:facet name="header">
									<h:outputText value="Resultado " style="display:hidden"  />
								</f:facet>
								    <h:panelGrid columns="3">
								    <h:inputText value="#{obj.resultadoConciliacion}"
									styleClass="borderText"  id="txt" disabled="#{!obj.soyEditable}"
									style="display:hidden; width : 120px;">
									
									<a4j:support event="onfocus" reRender="listado,com,resulConciliacion,cantCuponesCargados"  onsubmit="funcioncita(#{obj.fila});"
										action="#{ConciliacionCuponesBean.popupAltaLoteComercio.conciliarDesdeTexto}" oncomplete="refrescarResultados(#{obj.fila});">
										<f:param name="filita" value="#{obj.fila}"/> 
									 </a4j:support>  
									 </h:inputText>	
									    <a4j:commandLink value="" title="Click para editar el item"  ajaxSingle="true" reRender="listado" 
                    						  id="editarItem"  actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.habilitaFilaModificacion}" >
						               	   <x:graphicImage id ="resI" value="#{obj.iconoConciliacion}"/>
						               	   <a4j:actionparam name="filaAEditar" value="#{obj.fila}"/>
								        </a4j:commandLink>
								  </h:panelGrid>  
							</x:column>
                                       
                            <x:column>
								<f:facet name="header">
									<h:outputText value="Accion " style="display:hidden"  />
								</f:facet>
								    <h:panelGrid columns="2" >
								         <a4j:commandLink value="" title="Ejecuta conciliacion nuevamente con los datos que se modificaron"   
                    						   id="cancCambios" onclick="conciliarDesdeIcono(#{obj.fila})" >
						               	   <x:graphicImage id ="cancCambiosImg" value="/img/start_22x22.png"/>
						                </a4j:commandLink>
								       
								 	    <a4j:commandLink  title="Cancela los cambios hechos al item" reRender="listado" 
								 	   	  actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.conciliarDesdeLink}"  
					                		    id="saveItem"  ajaxSingle="true"  value="" >
						               	   <x:graphicImage id ="saveImg" value="/img/stop_22x22.png"/>
						               	    <a4j:actionparam name="filita" value="#{obj.fila}"/>
								        </a4j:commandLink>
								      					
							   	    </h:panelGrid>  
							   	     
						    </x:column>        
                                        
              

						</h:dataTable>
                    </c:if>
                     <h:outputText value="Atencion: Si un cupon no pertenece al comercio o  el codigo de autorizacion ya fue cargado en otro lote, el mismo sera omitido y no sera guardado " id="outMsgAdvertencia"
											styleClass="texto" style="color:green" rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarGuardar}"/>
					<h:panelGrid id="botonera" columns="8" width="700"  >
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						 <h:commandLink  id="showPopup"  immediate="true"
                           action="#{ConciliacionCuponesBean.popupAltaLoteComercio.llamarPopupConf}"  value=""/>
                        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                        <x:commandLink value="" title="Imprimir cabecera"  
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.imprimir }" id="print2"   rendered="#{!ConciliacionCuponesBean.popupAltaLoteComercio.mostrarFiltro && ConciliacionCuponesBean.popupAltaLoteComercio.mostrarGuardar}">
						<x:graphicImage id="imgPrint2" value="/img/printer48.png"></x:graphicImage>
						</x:commandLink>
						
						<x:commandLink value="" title="Guardar"  rendered="#{ConciliacionCuponesBean.popupAltaLoteComercio.mostrarGuardar}"
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.recargarYCerrarPopup}" id="li">
						<x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
						</x:commandLink>
						 
						<x:commandLink value="" title="Volver" 
							actionListener="#{ConciliacionCuponesBean.popupAltaLoteComercio.cancelar}"
							 id="liDos">
							<x:graphicImage id="imDos" value="/img/icon/back.gif"></x:graphicImage>
						</x:commandLink>
                   </h:panelGrid>
             	</h:panelGrid>

				<%-- Hasta aqui los cambios --%>





			</h:panelGroup>
           </x:div>
		</x:panelTabbedPane>


	</h:form></center>
	</body>
	</html>
</f:view>

