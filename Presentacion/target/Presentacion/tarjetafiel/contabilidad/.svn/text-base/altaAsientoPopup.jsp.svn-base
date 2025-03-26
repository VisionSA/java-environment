<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Busca Planes de Cuenta"/></title>
    <s:script language="javascript">
		
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
		
		
		
	</s:script>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
</head>
<%@include file="/inc/head.inc"%>

<body  onload="ocultarFila(); cargarArrayCuentas(); balancearHaberYDebe(); document.getElementById('buscarPlanCuentaForm:comprobante').focus();" onbeforeunload="ShowWait('buscarPlanCuentaForm');">


<script languaje="javascript">
         var listaDeCuentas = new Array();
         var listaModificados = new Array();
         var listaEliminados = new Array();
         var numero = 0;
         var numeroEliminado = 0;
         var palabra;
         var suma = 0;
         var sumaHaber = 0;
  		 var cadenaIni = "buscarPlanCuentaForm:listado:";
  		 var cadenaFin = ":deb";
  		 var cadenaFinHaber = ":hab";
  		 var estadoTabla = true;
  		 
  		 function redondea(sVal, nDec){
		    var n = parseFloat(sVal);
		    var s;
		    n = Math.round(n * Math.pow(10, nDec)) / Math.pow(10, nDec);
		    s = String(n) + "." + String(Math.pow(10, nDec)).substr(1);
		    s = s.substr(0, s.indexOf(".") + nDec + 1);
		    return s;
		  }
  		 
         function agregarAModificar(a) {
  		 	yaExiste=false;
  		 	for (i=0; i<listaModificados.length;i++) {
 		 	    if (listaModificados[i]==a) {
 		 	       yaExiste=true;  
 		 	    }
 		 	}
 		 	if (!yaExiste) {
 		 		listaModificados[numero]=a;
 		 		numero++;
 		 		if (numero>1) {
 		 		    document.getElementById('pp').value = document.getElementById('pp').value + ", " + a;
 		 		} else {
 		 		    document.getElementById('pp').value = a;
 		 		}
 		 	}
  		 }  		 
  		 
  		 function balancear(event) {
  		    var evento = window.event || event;
		    if (evento.keyCode == 13) {
				return false;
			}
  		    suma = 0;
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFin)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFin).value!=""  && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
 		 	    	suma = suma + parseFloat(document.getElementById(cadenaIni + i + cadenaFin).value);
 		 	    }    
 		 	}
  		    document.getElementById('buscarPlanCuentaForm:totDebeLote').value = redondea(suma, 2);
  		    cambiarColor();
  		 }
  		 function balancearHaber(event) {
  		    var evento = window.event || event;
		    if (evento.keyCode == 13) {
				return false;
			}
  		    sumaHaber = 0;
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFinHaber)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFinHaber).value!=""  && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
 		 	    	sumaHaber = sumaHaber + parseFloat(document.getElementById(cadenaIni + i + cadenaFinHaber).value);
 		 	    }    
 		 	}
  		    document.getElementById('buscarPlanCuentaForm:totHaberLote').value = redondea(sumaHaber, 2);
  		    cambiarColor();
  		    return true;
  		 }
  		 function balancearHaberYDebe() {
  		    
  		    sumaHaber = 0;
  		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFinHaber)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFinHaber).value!="" && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
 		 	    	sumaHaber = sumaHaber + parseFloat(document.getElementById(cadenaIni + i + cadenaFinHaber).value);
 		 	    }    
 		 	}
  		    document.getElementById('buscarPlanCuentaForm:totHaberLote').value = redondea(sumaHaber, 2);
  		    cambiarColor();
  		    suma = 0;
  		    for (i=0; document.getElementById(cadenaIni + i + cadenaFin)!=null;i++) {
 		 	    if (document.getElementById(cadenaIni + i + cadenaFin).value!=""  && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
 		 	    	suma = suma + parseFloat(document.getElementById(cadenaIni + i + cadenaFin).value);
 		 	    }    
 		 	}
  		    document.getElementById('buscarPlanCuentaForm:totDebeLote').value = redondea(suma, 2);
  		    cambiarColor();
  		    for (i=0; document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value!=""; i++ ) {
		     	    for (j=0; j<listaDeCuentas.length;j++) {
 		 	    		if (listaDeCuentas[j]==document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value) {
							
							if (listaDeCuentas[j+2]=="S") {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = '';
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.color = '#000000';
							} else {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = 'none';
							}
						} 
					}
			}

  		    return true;
  		 }
  		 function cambiarColor() {
  		     if (document.getElementById('buscarPlanCuentaForm:totDebeLote').value==document.getElementById('buscarPlanCuentaForm:totHaberLote').value) {
				 document.getElementById('buscarPlanCuentaForm:balanceoLote').bgColor = document.getElementById('buscarPlanCuentaForm:botonera').bgColor;
  		     } else {
  		         document.getElementById('buscarPlanCuentaForm:balanceoLote').bgColor = "red";
  		     }
  		 }
  		
  		  function bloquearCaja(InputText) {
  		      if (InputText.value!=""&&InputText.value!=null) { 
  		      		valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
  		      		} 
  		      } else {
  		            valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = false;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = false;
  		      		}
  		      }
  		      return true;
  		 }
  		 
  		 function bloquearCaja(InputText, event) {
  		      var evento = window.event || event;
		      if (evento.keyCode == 13) {
				  return false;
			  }
  		      if (InputText.value!=""&&InputText.value!=null) { 
  		      		valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
  		      		} 
  		      } else {
  		            valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
  		      		if (valor == "hab") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = false;
  		      		} 
  		      		if (valor == "deb") {
  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = false;
  		      		}
  		      }
  		 
  		 }
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


		function mostrarFila(i) {

		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.width = 30;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.height = 20;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.background = '#FFFFFF';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.border = '1px solid #666666';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.display = '';

		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.width = 120;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.height = 20;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.background = '#FFFFFF';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.border = '1px solid #666666';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.display = '';

		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.width = 100;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.height = 20;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.background = '#FFFFFF';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.border = '1px solid #666666';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.display = '';

		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.width = 100;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.height = 20;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.background = '#FFFFFF';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.border = '1px solid #666666';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.display = '';

		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.width = 130;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.height = 20;
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.background = '#FFFFFF';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.border = '1px solid #666666';
		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = '';


		    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':eliminarDetalleLoteLink').style.display = '';
			return null;
		}
		
		function ocultarFila() {
		    for (var i = 0; i < 100; i++) {
		        if (document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value==null || document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value=="") {
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':tit').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = 'none';
			        document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':listadoDeCentros').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':interno').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':eliminarDetalleLoteLink').style.display = 'none';
		    	} else {
		    		document.getElementById('buscarPlanCuentaForm:leyendaNombre').style.display = 'none';
                        document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').disabled = true;
                        document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').style.background = '#dddddd';
                        var InputText = document.getElementById('buscarPlanCuentaForm:listado:' + i + ':hab');
	                    if (InputText.value!=""&&InputText.value!=null) { 
		  		      		valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
		  		      		if (valor == "hab") {
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
		  		      		} 
		  		      		if (valor == "deb") {
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
		  		      		} 
			  		    } else {
			  		        valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
			  		      	if (valor == "hab") {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = false;
			  		      	} 
			  		      	if (valor == "deb") {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = false;
			  		      	}
			  		      }
		    		
                        InputText = document.getElementById('buscarPlanCuentaForm:listado:' + i + ':deb');
	                    if (InputText.value!=""&&InputText.value!=null) { 
		  		      		valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
		  		      		if (valor == "hab") {
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
		  		      		} 
		  		      		if (valor == "deb") {
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
		  		      		} 
			  		    } else {
			  		        valor = InputText.id.substring(InputText.id.length-3, InputText.id.length);
			  		      	if (valor == "hab") {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = false;
			  		      	} 
			  		      	if (valor == "deb") {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = false;
			  		      	}
			  		      }


		    	    document.getElementById('buscarPlanCuentaForm:listado:' + i + ':listadoDeCentros').style.display = 'none';
			    	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':interno').style.display = 'none';
			    	mostrarFila(i);
		    	}
		    }
		}
		
		function cargarArrayCuentas() {
			listaDeCuentas = document.getElementById('buscarPlanCuentaForm:tablaCuentas').value.split(":");
		}
		
		function captarEnter(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		        if (InputText.value!=""&&InputText.value!=null) {
                    var cadena = InputText.id;
 		 	    	cadena = cadena.substring(0,cadena.length - 3);
 		 	    	var cadenaDos;
 		 	    	var cadenaTres;
 		 	    	cadenaDos = cadena + "tit";
 		 	    	cadenaTres = cadena + "deb";
                    for (i=0; i<listaDeCuentas.length;i++) {
 		 	    		if (listaDeCuentas[i]==InputText.value) {
							document.getElementById(cadenaDos).value = listaDeCuentas[i+1];
							if (listaDeCuentas[i+2]=="S") {
								document.getElementById(cadena + 'verListado').style.color = '#000000';
								document.getElementById(cadena + 'verListado').style.display = '';
							} else {
								document.getElementById(cadena + 'verListado').style.display = 'none';
							}
                            document.getElementById(cadenaTres).focus();
                            return false;
						} 
					}
					InputText.value = "";
					document.getElementById(cadenaDos).value = "";
					document.getElementById(cadena + 'verListado').style.display = 'none';
					return false;	
 		 	    }
 		 	}
		    return true;
		}
		
		function captarEnterOnDebe(InputText, elEvento) {
		    
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
		        if (InputText.value!=""&&InputText.value!=null) {
 		 	    	cadena = cadena + "ley";
					document.getElementById(cadena).focus();
 		 	    } else {
 		 	    	cadena = cadena + "hab";
					document.getElementById(cadena).focus();
 		 	    }
 		 	    return false;
 		 	}
		    return true;
		}
		
		function captarEnterOnHaber(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
 		 	    cadena = cadena + "ley";
				document.getElementById(cadena).focus();
 		 	    return false;
 		 	}
		    return true;
		}		
				
	    function captarEnterEnLeyenda(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		        var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 3);
 		 	    var cadenaDos;
 		 	    var cadenaTres;
 		 	    var cadenaCuatro;
 		 	    cadenaDos = cadena + "cod";
 		 	    cadenaTres = cadena + "deb";
 		 	    cadenaCuatro = cadena + "hab";
		        if (document.getElementById(cadenaDos).value==""||( document.getElementById(cadenaTres).value=="" && document.getElementById(cadenaCuatro).value=="")) {
		            return false;
		        }
		    	var cadena = InputText.id;
		    	if (InputText.value == "" || InputText.value == null) {
		    		InputText.value = document.getElementById('buscarPlanCuentaForm:comprobante').value;
		    	}
		    	var cadenaAux;

		    	
 		 	    cadenaAux = cadena.substring(29,cadena.length - 4);

 		 	    var numFila = 0;
 		 	    numFila = 1 + parseInt(cadenaAux);
                mostrarFila(numFila);
 		 	    cadena = cadena.substring(0, 29) + numFila +  ":cod";
				document.getElementById(cadena).focus();
 		 	    return false;
 		 	}
		    return true;
		}		
		
		function captarEnterAFecha(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	if (InputText.value==""||InputText.value == null) {
		    	   return false;
		    	} else {
		    	   document.getElementById('FechaInicioAsiento').select();
		    	   document.getElementById('FechaInicioAsiento').focus();
 		 	       return false;
		    	}

 		 	}
		    return true;
		}
		
				
		function captarEnterPrincipal(cadenaFecha, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	if (cadenaFecha == ""||cadenaFecha == null) {
		    	   return false;
		    	} else {
		    	   if (cadenaFecha.length == 10) {
		    	        if (/^\d{2}\/\d{2}\/\d{4}$/.test(cadenaFecha)) {
		    	   			mostrarFila(0);
		    	   			document.getElementById('buscarPlanCuentaForm:listado:0:cod').focus();
 		 	       		}
 		 	       		return false;
 		 	       } else {
 		 	       		return false;
 		 	       }
		    	}
 		 	} 
		    return true;
		}		
		

		function verLeyenda(InputText) {
			if (InputText.value == "" || InputText.value == null) {
		    	document.getElementById('buscarPlanCuentaForm:leyendaNombre').style.display = '';
		    } else {
		    	document.getElementById('buscarPlanCuentaForm:leyendaNombre').style.display = 'none';
		    }
		}	
		
		function verCentroCosto(CommandLink){
		     var cc = CommandLink.id.substring(29,CommandLink.id.length - 11);
		     
		     document.getElementById('buscarPlanCuentaForm:listado:tly').style.display = 'none';
		     for (i=0;document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley')!=null ; i++ ) {
		     	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = 'none';
		     	
		     }
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':verListado').style.display = 'none';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros').style.display = '';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':interno').style.display = '';
		     
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':acepCentro').style.color = '#000000';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':acepCancel').style.color = '#000000';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':acepTotal').style.color = '#000000';
		     
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':totalCostos').style.width = 50;
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':totalCostos').style.height = 18;
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':totalCostos').style.background = '#FFFFFF';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':totalCostos').style.border = '1px solid #666666';
		     document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':totalCostos').style.display = '';
		   <%-- var elem = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:0:codCenCos');
		     if (elem!=null) { --%>
		         for (num = 0; document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':codCenCos')!=null; num++) {
		         	document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':codCenCos').style.color = '#000000';
		            document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':titDos').style.width = 50;
		            document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':titDos').style.height = 18;
		            document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':titDos').style.background = '#FFFFFF';
		            document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':titDos').style.border = '1px solid #666666';
		            document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':listadoDeCentros:' + num + ':titDos').style.display = '';
		           
		         }

		         var colDebe = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':deb').value;
 		 	     var colHaber = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':hab').value;
 		 	     if (colDebe!=null && colDebe!="") {
 		 	     	cc = parseFloat(colDebe);
 		 	     } else {
 		 	        cc = parseFloat(colHaber);
 		 	     }
		         comprobarSuma(document.getElementById('buscarPlanCuentaForm:listado:0:listadoDeCentros:0:titDos'),cc);
		     <%-- }--%>
		     return false;
		}
		
		function comprobarSuma(InputText, cc) {
		    var iniCad = InputText.id.substring(0,48);
		    var finCad = InputText.id.substring(InputText.id.length-7);
			var entero = 0;
			for (i=0; document.getElementById(iniCad + i + finCad)!=null;i++) {
 		 	    entero += parseFloat(document.getElementById(iniCad + i + finCad).value);
 		 	}
 		 	var cadena = iniCad.substring(0,iniCad.length - 18) +  ":totalCostos";
 		 	document.getElementById(cadena).value = entero;
			if (entero == parseFloat(cc)) {
				return true;
			} else {
				return false;
			}
		}
		
		function avanzarCentroCosto(InputText, elEvento) {
			var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		        var cadena = InputText.id;
 		 	    cadena = cadena.substring(0,cadena.length - 6);
 		 	    var iniCad = InputText.id.substring(0,48);
		    	var finCad = InputText.id.substring(InputText.id.length-7);
		    	var nume = InputText.id.substring(48,InputText.id.length-7);
 		 	    var inicioCadena = iniCad.substring(29,iniCad.length - 18);
 		 	    var cc = parseInt(inicioCadena);
 		 	    var colDebe = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':deb').value;
 		 	    var colHaber = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':hab').value;
 		 	    if (colDebe!=null && colDebe!="") {
 		 	    	cc = parseFloat(colDebe);
 		 	    } else {
 		 	        cc = parseFloat(colHaber);
 		 	    }
 		 	    if (comprobarSuma(InputText, cc)) {
		    		document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':verListado').style.display = '';
		  		    document.getElementById('buscarPlanCuentaForm:listado:tly').style.display = '';
		  		    for (i=0; i <= document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value!=""; i++ ) {
		     			document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = '';

		     		}
		 		    document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros').style.display = 'none';
		 		    document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':interno').style.display = 'none';
		 		    document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':ley').focus();
 		 	    } else {
 		 	        var x = parseInt(nume);
 		 	        x = parseInt(x) + 1;
 		 	        if (document.getElementById(iniCad + x + finCad)!=null) {
 		 	        	document.getElementById(iniCad + x + finCad).focus();
 		 	        	document.getElementById(iniCad + x + finCad).select();
 		 	        }
 		 	    }
		        
 		 	    return false;
 		 	}
		    return true;
		}
				
		function cancelarCuentas(cad) {
			var inicioCadena = cad.substring(29,cad.length - 11);
			document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':verListado').style.display = '';
		  	document.getElementById('buscarPlanCuentaForm:listado:tly').style.display = '';
		  	for (j=0; document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros:' + j + ':titDos')!=null;j++) {
		  	    document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros:' + j + ':titDos').value = 0;
		  	}
		  	document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':totalCostos').value = "";
		  	for (i=0; document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value!=""; i++ ) {
		     	document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = '';
		     	if (document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value!="") {
		     	    for (j=0; j<listaDeCuentas.length;j++) {
 		 	    		if (listaDeCuentas[j]==document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value) {
							
							if (listaDeCuentas[j+2]=="S") {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = '';
							} else {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = 'none';
							}
						} 
					}
		     	}
		     	
		    }
		 	document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros').style.display = 'none';
		 	document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':interno').style.display = 'none';
		 	document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':ley').focus();
		}		
		
		function aceptarCuentas(cad) {
			var inicioCadena = cad.substring(29,cad.length - 11);
			if (document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros:0:titDos')!=null) {
			    var cc = parseInt(inicioCadena);
 		 	    var colDebe = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':deb').value;
 		 	    var colHaber = document.getElementById('buscarPlanCuentaForm:listado:' + cc + ':hab').value;
 		 	    if (colDebe!=null && colDebe!="") {
 		 	    	cc = parseFloat(colDebe);
 		 	    } else {
 		 	        cc = parseFloat(colHaber);
 		 	    }
				if (comprobarSuma(document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros:0:titDos'), cc)) {
			 		document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':verListado').style.display = '';
		  			document.getElementById('buscarPlanCuentaForm:listado:tly').style.display = '';
		  			document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':totalCostos').value = "";
		  			for (i=0; document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value!=""; i++ ) {
		     			document.getElementById('buscarPlanCuentaForm:listado:' + i + ':ley').style.display = '';
		     			for (j=0; j<listaDeCuentas.length;j++) {
 		 	    		if (listaDeCuentas[j]==document.getElementById('buscarPlanCuentaForm:listado:' + i + ':cod').value) {
							
							if (listaDeCuentas[j+2]=="S") {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = '';
							} else {
								document.getElementById('buscarPlanCuentaForm:listado:' + i + ':verListado').style.display = 'none';
							}
						} 
					}
		    		}
		 			document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':listadoDeCentros').style.display = 'none';
		 			document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':interno').style.display = 'none';
				 	document.getElementById('buscarPlanCuentaForm:listado:' + inicioCadena + ':ley').focus();
			 	}
			 	
			}
		}
		
		function desabilitarHermano(SelectBooleanCheckbox) {
			var bool = SelectBooleanCheckbox.checked;
			if (document.getElementById('buscarPlanCuentaForm:asientoDeCierre')!=null) {
				document.getElementById('buscarPlanCuentaForm:asientoDeCierre').checked = false;
			}
			if (document.getElementById('buscarPlanCuentaForm:asientoDeApertura')!=null) {
				document.getElementById('buscarPlanCuentaForm:asientoDeApertura').checked = false;
			}
			if (bool) SelectBooleanCheckbox.checked = true;
        }
				
    </script>





<center>
<h:form id="buscarPlanCuentaForm"  style="width: 800;">
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="altaAsientos">
	
		<h:outputText value="Alta de Asientos" style="FONT-SIZE: large;" styleClass="texto" /> 
	    <%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>     
	
	    <s:fieldset id="datosNoEditables" legend="Información sobre el asiento">
		    <h:panelGrid id="filtroUno" columns="4" rendered="#{!PlanCuentaBean.isBusquedaDeArbol}" align="center">
		        
				<h:outputText value="Empresa:" styleClass="texto" rendered="false"/>
				<h:outputText value="#{AsientosBean.popupAltaAsiento.empresa}" rendered="false" styleClass="textoblue" />
				<h:outputText value="Ejercicio:" styleClass="texto" />
				<h:outputText value="#{AsientosBean.popupAltaAsiento.ejercicio}" styleClass="textoblue"/>       		            
	            <h:outputText value="Fecha Contable:" styleClass="texto" rendered="false"/>
				<h:outputText value="#{AsientosBean.popupAltaAsiento.fechaContab}" rendered="false" styleClass="textoblue" />
				<h:outputText value="Fecha Carga:" styleClass="texto"/>
				<x:inputCalendar id="fechaCargaDatos" monthYearRowClass="yearMonthHeader" disabled="true"
					weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
					currentDayCellClass="currentDayCell" value="#{AsientosBean.popupAltaAsiento.fechaActual}" renderAsPopup="true"
					styleClass="bordeceldainferior" style="width: 90px"
					popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					helpText="DD/MM/YYYY" 
					forceId="true"/>
			</h:panelGrid>
		</s:fieldset>
		<h:panelGrid columns="2" id="genera" width="800">
		<h:panelGrid columns="3" id="datosACargar" width="500" align="left">
		        
				<h:outputText value="Concepto:" styleClass="texto"/>
				<h:inputText id="comprobante" value="#{AsientosBean.popupAltaAsiento.concepto}" onkeydown="captarEnterAFecha(this,event);" onkeyup="verLeyenda(this);"  maxlength="40" style=" width : 177px;"/>	 	
					      		            
	            <h:outputText value="Origen:" styleClass="texto" rendered="false"/>
				<h:selectOneMenu id="lstOrigenes" rendered="false" value="#{AsientosBean.popupAltaAsiento.idOrigenSeleccionado}" binding="#{AsientosBean.popupAltaAsiento.menuOrigen}"
			       		styleClass="lista" onfocus="encender(this);" immediate="true"
			       		onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       		<f:selectItems value="#{AsientosBean.popupAltaAsiento.origenesItem}" id="selectSucumDos" />
			    </h:selectOneMenu>	
				<h:outputText value="Nro. de Comprobante:" styleClass="texto" rendered="false"/>
				 <h:inputText id="comp" rendered="false" value="#{AsientosBean.popupAltaAsiento.nroComprobante}" maxlength="12" onkeypress="return soloEnteros(this,event);"/>	    
		        <h:outputText value="Tipo De Asiento:" styleClass="texto" rendered="false"/>
				<h:selectOneMenu id="lstDeAsientos" disabled="true" rendered="false" value="#{AsientosBean.popupAltaAsiento.idTipoAsientoSeleccionado}" binding="#{AsientosBean.popupAltaAsiento.tipoAsiento}"
			       		styleClass="lista" onfocus="encender(this);" immediate="true"
			       		onblur="apagar(this);" style=" width : 200px;" onchange="submit();">
			       		<f:selectItems value="#{AsientosBean.popupAltaAsiento.tiposDeAsientosItem}" id="selectSucum" />
			    </h:selectOneMenu>
			    <h:outputText id="leyendaNombre" value="Debe especificar un nombre para el concepto." styleClass="texto" style="color:green; display:hidden"/>

		</h:panelGrid>
		<h:panelGrid id="generaDos" columns="2" width="300">
				<h:outputText value="Fecha Contable:" styleClass="texto"/>
				<x:inputCalendar id="FechaInicioAsiento" monthYearRowClass="yearMonthHeader" onkeydown="captarEnterPrincipal(this.value, event);"
					weekRowClass="weekHeader" popupButtonStyleClass="standard_bold" 
					currentDayCellClass="currentDayCell" value="#{AsientosBean.popupAltaAsiento.fechaContable}" renderAsPopup="true"
					styleClass="bordeceldainferior" style="width: 90px"
					popupDateFormat="dd/MM/yyyy" popupWeekString="#{example_messages['popup_week_string']}"
					helpText="DD/MM/YYYY" 
					forceId="true"/>
		</h:panelGrid>
		</h:panelGrid>
		<h:panelGrid id="tipoEspecialDeAsiento" columns="5" align="center">
				<h:outputText id="mostrarFiltros" value="Realizar Asiento de Apertura" rendered="#{AsientosBean.aperturaPermitida}"/>
				<h:selectBooleanCheckbox id="asientoDeApertura" value="#{AsientosBean.popupAltaAsiento.asientoDeApertura}" rendered="#{AsientosBean.aperturaPermitida}" onchange="desabilitarHermano(this)"/>
				<h:outputText id="mostrarFiltrosDos" value="Realizar Asiento De Cierre" rendered="#{AsientosBean.cierrePermitido}"/>
				<h:selectBooleanCheckbox id="asientoDeCierre" value="#{AsientosBean.popupAltaAsiento.asientoDeCierre}" rendered="#{AsientosBean.cierrePermitido}" onchange="desabilitarHermano(this)"/>
				<x:commandLink value="" title="Documentos Adjuntos" actionListener="#{AsientosBean.popupAltaAsiento.mostrarDocAdjuntosDesdePopup}" id="verDocumentoAdjuntoEnPopup"> 
				    <x:graphicImage id="imTres" value="/img/icon/book_red 32.png"></x:graphicImage>
				</x:commandLink>
		</h:panelGrid>
		
		<%-- Comienzan los cambios --%>
		
		<h:panelGrid id="panelDeLosDetallesdelLote">
		            <x:inputHidden id="pp" forceId="true" value="#{AsientosBean.listaDeModicadosEnString}"/>
		            <x:inputHidden id="ee" forceId="true" value="#{AsientosBean.listaDeEliminadosEnString}"/>
		            <h:inputHidden value="#{AsientosBean.listaCuentas}" id="tablaCuentas" />
		            
		            <c:if test="${AsientosBean.agregarL}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rows="100"
                            sortable="false" 
                            var="obj"
                            width="1000px"
                            rowStyle="border-width: 0;"
                            value="#{AsientosBean.lotesDetalles}"
                            preserveDataModel="false" 
                            >
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Código" id="out1"/>
                            </f:facet>
                            <h:inputText id="cod" onchange="agregarAModificar('#{obj.idLoteDetal}')" styleClass="bordeceldainferior" value="#{obj.loteDetalle.numeroImputa}" onkeydown="captarEnter(this, event);" onkeypress="return soloEnteros(this, event);"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden"/>
                        </x:column>

                        <x:column >
                            <f:facet name="header">
                                <h:outputText value="Título" style="width: 250px;" />
                            </f:facet>
                            <h:inputText value="#{obj.denominacion}" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" id="tit" disabled="true"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Debe" />
                            </f:facet>
                            <h:inputText value="#{obj.debe}" onclick="bloquearCaja(this,event);" onkeydown="captarEnterOnDebe(this, event);" styleClass="bordeceldainferior" id="deb" maxlength="20" onkeypress="return soloDecimalesPrecisos(this,event,2);" 
                                 style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" onchange="agregarAModificar('#{obj.idLoteDetal}')" onkeyup="balancear(event); bloquearCaja(this, event);"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Haber" />
                            </f:facet>
                            <h:inputText value="#{obj.haber}" onclick="bloquearCaja(this,event);" onkeydown="captarEnterOnHaber(this, event);" 
                            onkeypress="return soloDecimalesPrecisos(this,event,2);" id="hab" styleClass="bordeceldainferior" onchange="agregarAModificar('#{obj.idLoteDetal}')"
                             onkeyup="balancearHaber(event); bloquearCaja(this,event);" maxlength="20" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden"/>
                        </x:column>     
                        

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Leyenda" id="tly" style="display:hidden" />
                            </f:facet>
                            <h:inputText value="#{obj.loteDetalle.leyenda}" id="ley" onchange="agregarAModificar('#{obj.idLoteDetal}')"
                             onkeydown="captarEnterEnLeyenda(this,event);" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" styleClass="bordeceldainferior" />
                        </x:column>
                        
                        <x:column width="210px">
                            <f:facet name="header">
                                <h:outputText value="Centro de Costo" style="width: 250px;"/>
                            </f:facet>
                                            
                                            <x:outputLabel id="verListado" for="ley" value="Centro de Costos"  styleClass="textoBlue" onclick="verCentroCosto(this); agregarAModificar('#{obj.idLoteDetal}')" style="display:hidden; color: #dcdcdc; width : 300px;" ></x:outputLabel>
                                            <x:dataTable id="listadoDeCentros" sortable="true" var="objDos" value="#{obj.listaCentroCosto}" style="display:hidden; width : 300px;" preserveDataModel="false">
                                                    <x:column style="width: 200px;">
							                           <%-- <f:facet name="header">
							                                <h:outputText value="Descripción" id="outDos"/>
							                            </f:facet> --%>
							                            <x:outputLabel style="color: #dcdcdc; border:none; background:none; width : 200px;" for="titDos" id="codCenCos" styleClass="bordeceldainferior" value="#{objDos.descripcion}"/>
							                        </x:column>
							
							                        <x:column style="width: 80px;">
							                          <%--  <f:facet name="header">
							                                <h:outputText value="Importe" />
							                            </f:facet> --%>
							                            <h:inputText value="#{objDos.importe}" styleClass="bordeceldainferior" id="titDos" onkeydown="avanzarCentroCosto(this,event);" style="display:hidden; width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none;" onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
							                        </x:column>
							                        
                                            </x:dataTable>
                                            <h:panelGrid id="interno" columns="6" align="center">
                                                  <x:outputLabel id="acepCentro" value="Aceptar" styleClass="textoblue" style="color: #dcdcdc;" onclick="aceptarCuentas(this.id)" for="totalCostos"></x:outputLabel>
                                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                                  <x:outputLabel id="acepCancel" value="Borrar" styleClass="textoblue" style="color: #dcdcdc;" onclick="cancelarCuentas(this.id)" for="totalCostos"></x:outputLabel>
                                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                                  <x:outputLabel id="acepTotal" value="Total" style="color: #dcdcdc;" for="totalCostos"></x:outputLabel>
                                                  
                                                  <x:inputText id="totalCostos" disabled="true" styleClass="bordeceldainferior" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none;"></x:inputText>
                                            </h:panelGrid>
                            
                        </x:column>   
                        
                          
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Borrar" style="width: 250px;"/>
                            </f:facet>
                            <h:selectBooleanCheckbox id="eliminarDetalleLoteLink" value="#{obj.seBorra}" style="width: 0px; height: 0px; border: none; background-color: #dcdcdc;" onchange="balancearHaberYDebe()"/>
						</x:column>
                                            
                	</x:dataTable>
                    </c:if>
                    
                    <c:if test="${!empty AsientosBean.asientosDetalles}">
                    <x:dataTable id="listado"
                            styleClass="standardTable"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
                            rows="100"
                            sortable="false" 
                            var="obj"
                            width="1000px"
                            rowStyle="border-width: 0;"
                            value="#{AsientosBean.asientosDetalles}"
                            preserveDataModel="false" 
                            >
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Código" id="out1"/>
                            </f:facet>
                            <h:inputText id="cod" onchange="agregarAModificar('#{obj.idAsienDetal}')" styleClass="bordeceldainferior" value="#{obj.asientoDetalle.numeroImputa}" onkeydown="captarEnter(this, event);" onkeypress="return soloEnteros(this, event);"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden"/>
                        </x:column>

                        <x:column >
                            <f:facet name="header">
                                <h:outputText value="Título" style="width: 250px;" />
                            </f:facet>
                            <h:inputText value="#{obj.denominacion}" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" id="tit" disabled="true"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Debe" />
                            </f:facet>
                            <h:inputText value="#{obj.debe}" onclick="bloquearCaja(this,event);" onkeydown="captarEnterOnDebe(this, event);" styleClass="bordeceldainferior" id="deb" maxlength="20" onkeypress="return soloDecimalesPrecisos(this,event,2);" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" onchange="agregarAModificar('#{obj.idAsienDetal}')" onkeyup="balancear(event); bloquearCaja(this, event);"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Haber" />
                            </f:facet>
                            <h:inputText value="#{obj.haber}" onclick="bloquearCaja(this,event);" onkeydown="captarEnterOnHaber(this, event);" onkeypress="return soloDecimalesPrecisos(this,event,2);" id="hab" styleClass="bordeceldainferior" onchange="agregarAModificar('#{obj.idAsienDetal}')" onkeyup="balancearHaber(event); bloquearCaja(this,event);" maxlength="20" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden"/>
                        </x:column>     
                        

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Leyenda" id="tly" style="display:hidden" />
                            </f:facet>
                            <h:inputText value="#{obj.asientoDetalle.leyenda}" id="ley" onchange="agregarAModificar('#{obj.idAsienDetal}')" onkeydown="captarEnterEnLeyenda(this,event);" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; display:hidden" styleClass="bordeceldainferior"/>
                        </x:column>
                        
                        <x:column width="210px">
                            <f:facet name="header">
                                <h:outputText value="Centro de Costo" style="width: 250px;"/>
                            </f:facet>
                                            
                                            <x:outputLabel id="verListado" for="ley" value="Centros de Costos" styleClass="textoBlue" onclick="verCentroCosto(this); agregarAModificar('#{obj.idAsienDetal}')" style="display:hidden; color: #dcdcdc; width : 300px;"></x:outputLabel>
                                            <x:dataTable id="listadoDeCentros" sortable="true" var="objDos" value="#{obj.listaCentroCosto}" style="display:hidden; width: 300px;" preserveDataModel="false">
                                                    <x:column style="width: 200px;">
							                           <%-- <f:facet name="header">
							                                <h:outputText value="Descripción" id="outDos"/>
							                            </f:facet> --%>
							                            <x:outputLabel style="color: #dcdcdc; border:none; background:none; width : 200px;" for="titDos" id="codCenCos" styleClass="bordeceldainferior" value="#{objDos.descripcion}"/>
							                        </x:column>
							
							                        <x:column style="width: 80px;">
							                          <%--  <f:facet name="header">
							                                <h:outputText value="Importe" />
							                            </f:facet> --%>
							                            <h:inputText value="#{objDos.importe}" styleClass="bordeceldainferior" id="titDos" onkeydown="avanzarCentroCosto(this,event);" style="display:hidden; width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none;" onkeypress="return soloDecimalesPrecisos(this,event,2);"/>
							                        </x:column>
							                        
                                            </x:dataTable>
                                            <h:panelGrid id="interno" columns="6" align="center">
                                                  <x:outputLabel id="acepCentro" style="color: #dcdcdc;" value="Aceptar" styleClass="textoblue" onclick="aceptarCuentas(this.id)" for="totalCostos"></x:outputLabel>
                                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                                  <x:outputLabel id="acepCancel" style="color: #dcdcdc;" value="Borrar" styleClass="textoblue" onclick="cancelarCuentas(this.id)" for="totalCostos"></x:outputLabel>
                                                  <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                                  <x:outputLabel value="Total: "  id="acepTotal" style="color: #dcdcdc;" for="totalCostos"></x:outputLabel>
                                                  
                                                  <x:inputText id="totalCostos" disabled="true" styleClass="bordeceldainferior" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none;"></x:inputText>
                                            </h:panelGrid>
                            
                        </x:column>   
                        
                          
                        
                        <x:column>
							<f:facet name="header">
                                <h:outputText value="Borrar" style="width: 250px;"/>
                            </f:facet>
                            <h:selectBooleanCheckbox id="eliminarDetalleLoteLink" value="#{obj.seBorra}" style="width: 0px; height: 0px; border: none; background-color: #dcdcdc;" onchange="balancearHaberYDebe()"/>
						</x:column>
                                            
                	</x:dataTable>
                    </c:if>
                    
                    
                    
                    
        		<%@include file="/inc/paginator.jsp" %>
        		<h:panelGrid id="balanceoLote" columns="5" align="center" >
        		    <h:commandButton id="balanceLote" action="#{AsientosBean.calcularBalanceoLote}" rendered="false" value="Calcular balanceo" styleClass="botones" />
        			<h:outputText value="Total Debe: " styleClass="text"/>
        			<h:inputText id="totDebeLote" styleClass="bordeceldainferior" disabled="true">
        			</h:inputText>
        			<h:outputText value="Total Haber: " styleClass="text"/>
        			<h:inputText id="totHaberLote" styleClass="bordeceldainferior" disabled="true">
        			</h:inputText>
        		</h:panelGrid>
				</h:panelGrid>
		
		        <%-- Hasta aqui los cambios --%>
		
		
		
		<h:panelGrid id="botonera" columns="8" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				<x:commandLink value="" title="Guardar" actionListener="#{AsientosBean.popupAltaAsiento.recargarYCerrarPopup}" id="li"> 
				    <x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
				</x:commandLink>
				<x:commandLink value="" title="Volver" action="#{AsientosBean.popupAltaAsiento.cancelar}" onclick="window.close();" id="liDos"> 
				    <x:graphicImage id="imDos" value="/img/icon/back.gif"></x:graphicImage>
				</x:commandLink>

              
			</h:panelGrid>
		
	</h:panelGroup>
	</x:panelTabbedPane>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>