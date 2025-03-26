<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
	<title><h:outputText value="#{MovimientoBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amMovimientoForm').submit();
		}
		function popupPagina(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;};
		};
	</s:script>
</head>

<jsp:include page="/inc/includes.jsp" />
<%-- if('${MovimientoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY})}; --%>
<body class="hold-transition skin-blue sidebar-mini"  onbeforeunload="ShowWait('amMovimientoForm');" 
	onload="${MovimientoBean.popupReport}; balancearHaberYDebe(); cargarArrayCuentas(); ocultarFila(); if('${MovimientoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY})};">

	<script languaje="javascript">
	    var listaDeCuentas = new Array();
	    var listaModificados = new Array();
	    var listaEliminados = new Array();
	    var numero = 0;
	    var numeroEliminado = 0;
	    var palabra;
	    var suma = 0;
		var sumaHaber = 0;
		var cadenaIni = "amMovimientoForm:listado:";
		var cadenaFin = ":deb";
		var cadenaFinHaber = ":hab";
		var estadoTabla = true;

		 function redondea(sVal, nDec){
			    var n = parseFloat(sVal) * 100;
			    var s;
			    
			    n = Math.round(n);
			    s = String(n / 100 + 0.0001);
		    	s = s.substr(0, s.indexOf(".") + nDec + 1);
		    	
			    return s;
			  }
		
		function verLeyenda(InputText) {
			if (InputText.value == "" || InputText.value == null) {
		    	document.getElementById('amMovimientoForm:leyendaNombre').style.display = ''; 
		    } else {
		    	document.getElementById('amMovimientoForm:leyendaNombre').style.display = 'none';
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
		
		function captarEnterPrincipal(cadenaFecha, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		    	if (cadenaFecha == ""||cadenaFecha == null) {
		    	   return false;
		    	} else {
		    	   if (cadenaFecha.length == 10) {
		    	        if (/^\d{2}\/\d{2}\/\d{4}$/.test(cadenaFecha)) {
		    	   			mostrarFila(0);
		    	   			document.getElementById('amMovimientoForm:listado:0:cod').focus();
 		 	       		}
 		 	       		return false;
 		 	       } else {
 		 	       		return false;
 		 	       }
		    	}
 		 	} 
		    return true;
		}		

		// Busca la cuenta y la leyenda en la tabla
		function captarEnter(InputText, elEvento) {
		    var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		        if (InputText.value!=""&&InputText.value!=null) {
                    var cadena = InputText.id;
 		 	    	cadena = cadena.substring(0,cadena.length - 3);
 		 	    	var cadenaDos = cadena + "tit";
 		 	    	var cadenaTres = cadena + "deb";
                    for (i=0; i<listaDeCuentas.length;i++) {
 		 	    		if (listaDeCuentas[i]==InputText.value) {
							document.getElementById(cadenaDos).value = listaDeCuentas[i+1];
                            document.getElementById(cadenaTres).focus();
                            return false;
						} 
					}
					InputText.value = "";
					document.getElementById(cadenaDos).value = "";
					return false;	
 		 	    }
 		 	}
		    return true;
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
	   		    document.getElementById('amMovimientoForm:totDebeLote').value = redondea(suma, 2);
	   		    cambiarColor();
	   		 }

	  		 function cambiarColor() {
	  		     if (document.getElementById('amMovimientoForm:totDebeLote').value==document.getElementById('amMovimientoForm:totHaberLote').value) {
					 document.getElementById('amMovimientoForm:balanceoLote').bgColor = document.getElementById('amMovimientoForm:panelBotonera').bgColor;
	  		     } else {
	  		         document.getElementById('amMovimientoForm:balanceoLote').bgColor = "red";
	  		     }
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
	  		    document.getElementById('amMovimientoForm:totHaberLote').value = redondea(sumaHaber, 2);
	  		    cambiarColor();
	  		    return true;
	  		 }

		    function captarEnterEnLeyenda(InputText, elEvento) {
			    var evento = window.event || elEvento;
			    if (evento.keyCode == 13) {
			        var cadena = InputText.id;
	 		 	    cadena = cadena.substring(0,cadena.length - 3);
	 		 	    var cadenaDos = cadena + "cod";
	 		 	    var cadenaTres = cadena + "deb";
	 		 	    var cadenaCuatro = cadena + "hab";
			        if (document.getElementById(cadenaDos).value==""||( document.getElementById(cadenaTres).value=="" && document.getElementById(cadenaCuatro).value=="")) {
			            return false;
			        }
			    	var cadena = InputText.id;
			    	if (InputText.value == "" || InputText.value == null) {
			    		InputText.value = document.getElementById('amMovimientoForm:comprobante').value;
			    	}
			    	var cadenaAux;

			    	
	 		 	    cadenaAux = cadena.substring(25,cadena.length - 4);

	 		 	    var numFila = 0;
	 		 	    numFila = 1 + parseInt(cadenaAux);
	                mostrarFila(numFila);
	 		 	    cadena = cadena.substring(0, 25) + numFila +  ":cod";
					document.getElementById(cadena).focus();
	 		 	    return false;
	 		 	}
			    return true;
			}	

	  		 function balancearHaberYDebe() {
	   		    sumaHaber = 0;
	   		 	for (i=0; document.getElementById(cadenaIni + i + cadenaFinHaber)!=null;i++) {
	  		 	    if (document.getElementById(cadenaIni + i + cadenaFinHaber).value!="" && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
	  		 	    	sumaHaber = sumaHaber + parseFloat(document.getElementById(cadenaIni + i + cadenaFinHaber).value);
	  		 	    }    
	  		 	}
	   		    document.getElementById('amMovimientoForm:totHaberLote').value = redondea(sumaHaber, 2);
	   		    cambiarColor();
	   		    suma = 0;
	   		    for (i=0; document.getElementById(cadenaIni + i + cadenaFin)!=null;i++) {
	  		 	    if (document.getElementById(cadenaIni + i + cadenaFin).value!=""  && !document.getElementById(cadenaIni + i + ':eliminarDetalleLoteLink').checked) {
	  		 	    	suma = suma + parseFloat(document.getElementById(cadenaIni + i + cadenaFin).value);
	  		 	    }    
	  		 	}
	   		    document.getElementById('amMovimientoForm:totDebeLote').value = redondea(suma, 2);
	   		    cambiarColor();
	   		    for (i=0; document.getElementById('amMovimientoForm:listado:' + i + ':cod').value!=""; i++ ) {
	 		     	    for (j=0; j<listaDeCuentas.length;j++) {
	  		 	    		if (listaDeCuentas[j]==document.getElementById('amMovimientoForm:listado:' + i + ':cod').value) {
	 							
	 							if (listaDeCuentas[j+2]=="S") {
	 								document.getElementById('amMovimientoForm:listado:' + i + ':verListado').style.display = '';
	 								document.getElementById('amMovimientoForm:listado:' + i + ':verListado').style.color = '#000000';
	 							} else {
	 								document.getElementById('amMovimientoForm:listado:' + i + ':verListado').style.display = 'none';
	 							}
	 						} 
	 					}
	 			}

	   		    return true;
	   		 }
	   		 
	 		function mostrarFila(i) {

			    document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.width = 30;
			    document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.height = 20;
			    document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.background = '#FFFFFF';
			    document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.border = '1px solid #666666';
			    document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.display = '';

			    document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.width = 200;
			    document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.height = 20;
			    document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.background = '#FFFFFF';
			    document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.border = '1px solid #666666';
			    document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.display = '';

			    document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.width = 100;
			    document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.height = 20;
			    document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.background = '#FFFFFF';
			    document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.border = '1px solid #666666';
			    document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.display = '';

			    document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.width = 100;
			    document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.height = 20;
			    document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.background = '#FFFFFF';
			    document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.border = '1px solid #666666';
			    document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.display = '';

			    document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.width = 130;
			    document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.height = 20;
			    document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.background = '#FFFFFF';
			    document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.border = '1px solid #666666';
			    document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.display = '';

//				if(i == 0){
//			    document.getElementById('amMovimientoForm:listado:' + i + ':verAdminChequePopup').style.display = 'none';
//				    document.getElementById('amMovimientoForm:listado:' + i + ':eliminarDetalleLoteLink').style.display = 'none';
//				} else {
				   document.getElementById('amMovimientoForm:listado:' + i + ':verAdminChequePopup').style.display = '';
				   document.getElementById('amMovimientoForm:listado:' + i + ':eliminarDetalleLoteLink').style.display = '';
//				}
				return null;
			}

			function ocultarFila() {
			    for (var i = 0; i < 20; i++) {
			        if (document.getElementById('amMovimientoForm:listado:' + i + ':cod').value==null || document.getElementById('amMovimientoForm:listado:' + i + ':cod').value=="") {
				    	document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':tit').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':deb').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':hab').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':ley').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':verAdminChequePopup').style.display = 'none';
				    	document.getElementById('amMovimientoForm:listado:' + i + ':eliminarDetalleLoteLink').style.display = 'none';
			    	} else {
			    		document.getElementById('amMovimientoForm:leyendaNombre').style.display = 'none';
	                        document.getElementById('amMovimientoForm:listado:' + i + ':cod').disabled = true;
	                        document.getElementById('amMovimientoForm:listado:' + i + ':cod').style.background = '#dddddd';
	                        var InputText = document.getElementById('amMovimientoForm:listado:' + i + ':hab');
		                    if (InputText.value!=""&&InputText.value!=null) { 
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true;
				  		    } else {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "deb").disabled = true; //false;
				  		    }
	                        InputText = document.getElementById('amMovimientoForm:listado:' + i + ':deb');
		                    if (InputText.value!=""&&InputText.value!=null) { 
		  		      		    document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;
				  		    } else {
			  		      		document.getElementById(InputText.id.substring(0,InputText.id.length-3) + "hab").disabled = true;//false;
				  		    }
				    	mostrarFila(i);
			    	}
			    }
			}

			function cargarArrayCuentas() {
				listaDeCuentas = document.getElementById('amMovimientoForm:tablaCuentas').value.split(":");
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

	</script>
	<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${MovimientoBean.tituloCorto}
    <small>${MovimientoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>
	<secure:check/>

	<h:form id="amMovimientoForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!MovimientoBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >

			

			<f:facet name="body">
				<h:panelGroup id="body">
					
					<h:panelGrid columns="1" align="center" id="PanelPricipal">
					<%-- INCLUDE PARA LOS ERRORES --%>
					<h:panelGroup id="errores">
						<jsp:include page="/inc/error.jsp" />
					</h:panelGroup>

					<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
					<s:modalDialog dialogId="viewDialog"
								dialogVar="viewDialog"
								styleClass="viewDialog"
								dialogTitle="#{MovimientoBean.tituloCorto}">
						<h:panelGrid columns="2" width="500">
							<x:graphicImage value="/img/#{MovimientoBean.popup.nombreIcono}" />
							<h:outputText value="#{MovimientoBean.popup.mensaje}" styleClass="texto"/>
						</h:panelGrid>
						<h:panelGrid columns="3" width="500">
							<x:commandButton action="#{MovimientoBean.irANuevoMovimiento}" 
								onclick="clickLink('nuevoMovimiento');dojo.widget.byId('viewDialog').hide();"
								value="Nuevo" styleClass="botones" title="Agregar nueva."/>
							<x:commandButton action="#{MovimientoBean.irAImprimirComprobante}" 
								onclick="clickLink('imprimirComprobante');dojo.widget.byId('viewDialog').hide();"
								value="Imprimir" styleClass="botones" title="Seguir modificando."/>
							<x:commandButton action="#{MovimientoBean.irAListarMovimiento}" 
								onclick="clickLink('listarMovimiento');dojo.widget.byId('viewDialog').hide();"
								value="Listar" styleClass="botones" title="Ir al listado."/>
						</h:panelGrid>
					</s:modalDialog>
					<x:commandLink id="nuevoMovimiento" action="#{MovimientoBean.irANuevoMovimiento}" forceId="true" style="display: block;"/>
					<x:commandLink id="imprimirComprobante" action="#{MovimientoBean.irAImprimirComprobante}" forceId="true" style="display: block;"/>
					<x:commandLink id="listarMovimiento" action="#{MovimientoBean.irAListarMovimiento}" forceId="true" style="display: block;"/>
					
					<h:panelGrid id="uno" columns="1" width="850" align="center">
					<s:fieldset legend="Movimiento">
						<h:panelGrid id="panelPrincipalUno" columns="2" width="550" align="center">
							<h:outputText value="Concepto: " styleClass="texto" style="margin-bottom:8px;"/>
							<h:selectOneMenu id="lstConcepto" value="#{MovimientoBean.idConceptoSeleccionada}"
								valueChangeListener="#{MovimientoBean.cambioConcepto}" onchange="submit();"
								styleClass="lista" immediate="true" onfocus="encender(this);"
								onblur="apagar(this);" style=" width : 300px; margin-bottom:8px;" 
								binding="#{MovimientoBean.conceptoHtml}"
								disabled="#{MovimientoBean.verDetalles}">
								<f:selectItems value="#{MovimientoBean.conceptoItems}"/>
							</h:selectOneMenu>

							<h:outputText value="Tipo de Concepto: " styleClass="texto" 
										rendered="#{MovimientoBean.verDetalles}"/>
							<h:outputText value="#{MovimientoBean.clase.descripcion}" styleClass="texto" 
									    rendered="#{MovimientoBean.verDetalles}" style="color:green;"/>
						</h:panelGrid>
					</s:fieldset>
					
					<f:verbatim><br></f:verbatim>
					
				<c:if test="${MovimientoBean.verDetalles}">
					<h:panelGrid columns="2" id="genera" width="800">
						<h:panelGrid columns="3" id="datosACargar" width="500" align="left">
							<h:outputText id="outConcepto" value="Concepto:" styleClass="texto"  style="margin-bottom:8px;"/>
							<h:inputText id="comprobante" value="#{MovimientoBean.popupAltaAsiento.asiento.concepto}" 
									onkeydown="captarEnterAFecha(this,event);" 
									onkeyup="verLeyenda(this);"  maxlength="40" style=" width : 177px; margin-bottom:8px;"/>	 	
						    <h:outputText id="leyendaNombre" value="Debe especificar un nombre para el concepto." 
						    		styleClass="texto" style="color:green; display:hidden; margin-bottom:8px;"/>
							<h:outputText id="outCodExterno" value="Codigo Externo:" styleClass="texto" style="margin-bottom:8px;"/>
							<h:inputText id="codExterno" value="#{MovimientoBean.movimiento.codigoExterno}" 
									onkeypress="return soloEnteros(this,event);"
									onkeydown="captarEnterAFecha(this,event);" 
									maxlength="40" style=" width : 177px; margin-bottom:8px;"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</h:panelGrid>
						<h:panelGrid id="generaDos" columns="2" width="300">
								<h:outputText value="Fecha Contable:" styleClass="texto"/>
								<f:verbatim>
						                <div class="input-group date">
						                    <div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
						                    <input type="text" class="form-control pull-right" id="fDesde" autocomplete="off">
						                </div>
								</f:verbatim>
								<h:inputText id="FechaDesde" value="#{MovimientoBean.popupAltaAsiento.asiento.fecha}" style="display: none;" >
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText>
						</h:panelGrid>
					</h:panelGrid>
					
					<f:verbatim><br></f:verbatim>
		
                    <x:dataTable id="listado"
                            styleClass="table-bordered"
                            headerClass="dataTable_Header"
                            footerClass="standardTable_Header"
							columnClasses="standardTable_ColumnCentered"
                            sortable="false" 
                            var="obj"
                            width="1000px"
                            rowStyle="border-width: 0;"
                            value="#{MovimientoBean.asientosDetalles}"
                            preserveDataModel="false" 
                            >
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Código" id="out1"/>
                            </f:facet>

                            <h:inputText id="cod" 
                            		value="#{obj.asientoItem.idPlanCuenta}" onkeydown="captarEnter(this, event);" 
                            		onkeypress="return soloEnteros(this, event);"
                            		styleClass="bordeceldainferior"  
 									onchange="agregarAModificar('#{obj.idAsienDetal}');" 
                            		style="width: 0px; height: 0px; border:none; display:hidden;">
									
								<a4j:support event="onchange" action="#{obj.actualizarMedio}" reRender="comprobante">
									<f:param name="filaSeleccionada" value="#{obj.idAsienDetal}"/> 
								 </a4j:support>  
							 </h:inputText>	
                        </x:column>

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Título" style="width: 250px;" />
                            </f:facet>
                            <h:inputText value="#{obj.denominacion}" 
                            		style="width: 0px; height: 0px; border:none; display:hidden;" 
                            		id="tit" disabled="true"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Debe" />
                            </f:facet>
                            <h:inputText value="#{obj.debe}" 
		                            onclick="bloquearCaja(this,event);" onkeydown="captarEnterOnDebe(this, event);" 
		                            styleClass="bordeceldainferior" id="deb" maxlength="20" onkeypress="return soloDecimalesPrecisos(this,event,2);" 
		                            style="width: 0px; height: 0px; border:none; display:hidden;" 
		                            onchange="bloquearCaja(this,event); agregarAModificar('#{obj.idAsienDetal}')" onkeyup="balancear(event); bloquearCaja(this, event);"/>
                        </x:column>
                        
                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Haber" />
                            </f:facet>
                            <h:inputText value="#{obj.haber}" onclick="bloquearCaja(this,event);" 
                            		onkeydown="captarEnterOnHaber(this, event);" onkeypress="return soloDecimalesPrecisos(this,event,2);" 
                            		id="hab" styleClass="bordeceldainferior" onchange="agregarAModificar('#{obj.idAsienDetal}')" 
                            		onkeyup="balancearHaber(event); bloquearCaja(this,event);" maxlength="20" 
                            		style="width: 0px; height: 0px; border:none; display:hidden;"/>
                        </x:column>     

                        <x:column>
                            <f:facet name="header">
                                <h:outputText value="Leyenda" id="tly" />
                            </f:facet>
                            <h:inputText value="#{obj.asientoItem.leyenda}" id="ley" 
                            		onchange="agregarAModificar('#{obj.idAsienDetal}')"  
                         			style="width: 0px; height: 0px; border:none; display:hidden;" 
                         			styleClass="bordeceldainferior"/>
                        </x:column>
							<%--onkeydown="captarEnterEnLeyenda(this,event);" --%>

						<x:column>
							<f:facet name="header">
                                <h:outputText value="Medio" style="width: 250px;"/>
                            </f:facet>
							<x:commandLink actionListener="#{obj.agregarMedio}"  
									value="" title="Armar Medio" id="verAdminChequePopup" 
                            		style="width: 0px; height: 0px; border: none; display:hidden;">
							    <x:graphicImage id="imTres" value="/img/icon/book_green 24.png"/>
							</x:commandLink>
						</x:column>

                        <x:column>
							<f:facet name="header">
                                <h:outputText value="Borrar" style="width: 250px;"/>
                            </f:facet>
                            <h:selectBooleanCheckbox id="eliminarDetalleLoteLink" value="#{obj.seBorra}" 
                            		style="width: 0px; height: 0px; border: none; display:hidden;"
                            		onchange="balancearHaberYDebe()"/>
						</x:column>
                	</x:dataTable>

					<h:panelGrid id="balanceoLote" columns="4" align="center" style="margin-top:20px">
						<h:outputText value="Total Debe: " styleClass="text" style="margin-right:10px"/>
						<h:inputText id="totDebeLote" styleClass="bordeceldainferior" disabled="true" style="margin-right:20px"/>
						<h:outputText value="Total Haber: " styleClass="text" style="margin-right:10px"/>
						<h:inputText id="totHaberLote" styleClass="bordeceldainferior" disabled="true"/>
					</h:panelGrid>

                    </c:if>
					</h:panelGrid>
					<f:verbatim>
						<br>
					</f:verbatim>
					
					<f:verbatim><hr align="center" width="700"></f:verbatim>
					<h:panelGrid columns="10" width="727" id="panelBotonera">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<x:commandLink value="" title="Guardar" actionListener="#{MovimientoBean.grabar}" id="buttonGrabar"> 
						    <x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
						</x:commandLink>
						<x:commandLink value="" title="Cancelar" action="#{MovimientoBean.cancelar}" id="libuttonCancelar"> 
						    <x:graphicImage id="imDos" value="/img/icon/back.gif"></x:graphicImage>
						</x:commandLink>
					</h:panelGrid>
					</h:panelGrid>
					<x:inputHidden value="#{MovimientoBean.listaCuentas}" id="tablaCuentas"/>
				</h:panelGroup>
			</f:facet>
			
		</x:panelLayout>


	</h:form>
</center>

<div class="box-header"></div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{MovimientoBean.inicializar}") + `</li>`;
}
</script>

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "bottom"
    });
    
    
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("amMovimientoForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("amMovimientoForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";


    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amMovimientoForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>




</body>
</html>
</f:view>
