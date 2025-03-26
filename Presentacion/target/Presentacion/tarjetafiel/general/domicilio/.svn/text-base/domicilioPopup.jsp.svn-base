<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
    <title><h:outputText value="Tarjeta Fiel - Agregar domicilio"/></title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<script type="text/javascript">
	    function validarLetras(e) { 
		    tecla = (document.all) ? e.keyCode : e.which; 
		    if ((tecla==8) || (tecla==0))  return true; 
		    patron =/[A-Za-z\s]/; 
		    te = String.fromCharCode(tecla);
		    return patron.test(te); 
		} 
	</script>
  	
  	
</head>

<%@include file="/inc/head.inc" %>

<body onbeforeunload="ShowWait('agregarDomicilioForm');">
<center>
<h:form id="agregarDomicilioForm">
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup>
		<h:outputText value="Agregar Domicilio" style="FONT-SIZE: large;" styleClass="texto"/>
		
		<%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>            		
		
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>					
		<f:verbatim>&nbsp;</f:verbatim>
		<f:verbatim>&nbsp;</f:verbatim>
					

              		<h:panelGrid columns="6">
		                <h:outputText id="outTDireccion" value="T. Dirección: " styleClass="texto" rendered="#{DomicilioBean.boolTDireccion}"/>
						<h:selectOneMenu id="lstTipoDireccion" value="#{DomicilioBean.tipoDomicilioSeleccionado}" 
									rendered="#{DomicilioBean.boolTDireccion}"
		        					styleClass="lista" onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;">
	       					<f:selectItems value="#{DomicilioBean.listaTipoDomicilio}" id="selectTDomicilio"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " rendered="#{DomicilioBean.boolTDireccion}"/>
						<h:outputText id="outNombreCalle" value="Calle: " styleClass="texto"/>
		                <h:inputText id="nombreCalle" value="#{DomicilioBean.domicilio.calleNombre}" 
	                			 	 size="50" maxlength="50" styleClass="bordeceldatext" style="width: 300px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
	                	

		                <h:outputText id="outOrientacion" value="Orientación: " styleClass="texto"/>
						<h:selectOneMenu id="lstOrientacion" value="#{DomicilioBean.orientSeleccionada}" 
		        					 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);">
	       						<f:selectItems value="#{DomicilioBean.listaOrientacion}" id="selectOrientacion"/>
		        		</h:selectOneMenu>						
						<h:outputText value="    " />
						<h:outputText id="outNro" value="Nro: " styleClass="texto"/>
		                <h:inputText id="numeroCalle" value="#{DomicilioBean.domicilio.calleNumero}" 
	                			 	 size="5" maxlength="5" styleClass="bordeceldainferior" style="width: 40px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="    " />
	                		
	     
						<h:outputText id="outMonoblock" value="Monoblock: " styleClass="texto"/>
		                <h:inputText id="inMonoblock" value="#{DomicilioBean.domicilio.monoblock}" 
	                			 	 size="4" maxlength="4" styleClass="bordeceldainferior" style="width: 30px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>		                
						<h:outputText value="    " />
						<h:outputText id="outManzana" value="Manzana: " styleClass="texto"/>
		                <h:inputText id="manzana" value="#{DomicilioBean.domicilio.manzana}" 
	                			 	 size="10" maxlength="10" styleClass="bordeceldainferior" style=" width : 50px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
	                	
		     
						<h:outputText id="outArea" value="Area: " styleClass="texto"/>
		                <h:inputText id="area" value="#{DomicilioBean.domicilio.areaSector}" 
	                			 	 size="10" maxlength="10" styleClass="bordeceldatext" style="width: 60px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
						<h:outputText id="outPiso" value="Piso: " styleClass="texto"/>
		                <h:inputText id="piso" value="#{DomicilioBean.domicilio.piso}" 
	                			 	 size="2" maxlength="2" styleClass="bordeceldainferior" style="width: 22px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"
	                			 	 onkeypress="return soloEnteros(this,event);"/>
						<h:outputText value="    " />

              		    
						<h:outputText id="outDpto" value="Dpto: " styleClass="texto"/>
		                <h:inputText id="departamento" value="#{DomicilioBean.domicilio.depto}" 
	                			 	 size="3" maxlength="3" styleClass="bordeceldatext" style=" width : 25px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
		                <h:outputText id="outPais" value="Pais: " styleClass="texto"/>
						<h:selectOneMenu id="lstPais" value="#{DomicilioBean.idPaisSeleccionado}"
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     binding="#{DomicilioBean.paises}" style=" width : 200px;"
		        					     valueChangeListener="#{DomicilioBean.cambiarProvincias}" 
		        					     onchange="setValueId('lstProvincia','idFoco'); submit();">
	       					<f:selectItems value="#{DomicilioBean.listaPaises}" id="selectPaises"/>
		        		</h:selectOneMenu>
              			<h:outputText value="    " />
              			

		                <h:outputText id="outProvincia" value="Provincia: " styleClass="texto"/>
						<h:selectOneMenu id="lstProvincia" value="#{DomicilioBean.idProvinciaSeleccionada}" 
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     onchange="setValueId('lstPartido','idFoco'); submit();" 
		        					     binding="#{DomicilioBean.provincias}" style=" width : 200px;"
		        					     valueChangeListener="#{DomicilioBean.cambiarPartidos}">
	       					<f:selectItems value="#{DomicilioBean.listaProvincias}" id="selectProvincias"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " />
		                <h:outputText id="outPartido" value="Partido: " styleClass="texto"/>
						<h:selectOneMenu id="lstPartido" value="#{DomicilioBean.idPartidoSeleccionado}" 
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     binding="#{DomicilioBean.partidos}" 
		        					     onchange="setValueId('lstLocalidad','idFoco'); submit();" style=" width : 200px;"
		        					     valueChangeListener="#{DomicilioBean.cambiarLocalidades}">
	       					<f:selectItems value="#{DomicilioBean.listaPartidos}" id="selectPartidos"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " />

                		
		                <h:outputText id="outLocalidad" value="Localidad: " styleClass="texto"/>
						<h:selectOneMenu id="lstLocalidad" value="#{DomicilioBean.idLocalidadSeleccionada}" 
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     binding="#{DomicilioBean.localidades}" 
		        					     onchange="setValueId('lstBarrio','idFoco'); submit();" 
		        					     style=" width : 200px;" valueChangeListener="#{DomicilioBean.cambiarBarrios}">
	       					<f:selectItems value="#{DomicilioBean.listaLocalidades}" id="selectLocalidades"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " />
		                <h:outputText id="outBarrio" value="Barrio: " styleClass="texto"/>
						<h:selectOneMenu id="lstBarrio" value="#{DomicilioBean.idBarrioSeleccionado}" 
		        					     styleClass="lista" onfocus="encender(this);" onblur="apagar(this);"
		        					     binding="#{DomicilioBean.barrios}" style=" width : 200px;"
		        					     valueChangeListener="#{DomicilioBean.cambiarCodigoPostal}" 
		        					     onchange="setValueId('inCPA1','idFoco'); submit();">
	       					<f:selectItems value="#{DomicilioBean.listaBarrios}" id="selectBarrios"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " />

	                	
              			<h:outputText id="outCPA" value="CPA: " styleClass="texto"/>
						<h:panelGroup>
			                <h:inputText id="inCPA1" value="#{DomicilioBean.domicilio.cpa1}" 
		                			 	 size="3" maxlength="1" styleClass="bordeceldainferior" style="width: 32px" onkeypress="return validarLetras(event)"
		                			 	 onchange="this.value=this.value.toUpperCase(); setValueId('inCPA2','idFoco'); submit();"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inCPA2" value="#{DomicilioBean.domicilio.cpa2}" 
		                			 	 size="4" maxlength="4" styleClass="bordeceldainferior" style="width: 50px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);" binding="#{DomicilioBean.codigoPostal}"
	    	            			 	 onchange="setValueId('inCPA3','idFoco'); submit();"
	        	        			 	 onkeypress="return soloEnteros(this,event);"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inCPA3" value="#{DomicilioBean.domicilio.cpa3}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 50px" onkeypress="return validarLetras(event)"
		                			 	 onchange="this.value=this.value.toUpperCase(); setValueId('inGenerico','idFoco'); submit();"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"/>
	                	</h:panelGroup>
						<h:outputText value="    "/>
						<h:outputText id="outCPostal" value="C.Postal: " styleClass="texto"/>
		                <x:inputText id="cPostal" value="#{DomicilioBean.codPostalCompleto}"
		                			 styleClass="bordeceldainferior" disabled="true" style=" width : 100px;"/>
						<h:outputText value="    " />
              			

              			<h:outputText id="outGenerico" value="Generico: " styleClass="texto"/>
		                <h:inputText id="inGenerico" value="#{DomicilioBean.domicilio.generico}" 
	                			 	 styleClass="bordeceldatext" style="width: 300px"
	                			 	 onfocus="encender(this);" onblur="apagar(this);"/>
						<h:outputText value="    " />
						<h:outputText id="outLatitud" value="Latitud: " styleClass="texto" rendered="#{DomicilioBean.boolLatitud}"/>
						<h:panelGroup rendered="#{DomicilioBean.boolLatitud}">
			                <h:inputText id="inLatiGrados" value="#{DomicilioBean.domicilio.latGrados}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 32px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloEnteros(this,event);"/>
	                		<h:outputText value="°" styleClass="texto"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inLatiMinu" value="#{DomicilioBean.domicilio.latMinutos}" 
		                			 	 size="2" maxlength="2" styleClass="bordeceldainferior" style="width: 22px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloDecimales(this,event);"/>
	                		<h:outputText value="'" styleClass="texto"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inLatiSeg" value="#{DomicilioBean.domicilio.latSegundos}" 
		                			 	 size="2" maxlength="2" styleClass="bordeceldainferior" style="width: 22px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloDecimales(this,event);"/>
	                		<h:outputText value="''" styleClass="texto"/>
	                	</h:panelGroup>
						<h:outputText value="    " rendered="#{DomicilioBean.boolLatitud}"/>
						

						<h:outputText id="outLongitud" value="Longitud: " styleClass="texto" rendered="#{DomicilioBean.boolLongitud}"/>
						<h:panelGroup rendered="#{DomicilioBean.boolLongitud}">
			                <h:inputText id="inLonGrados" value="#{DomicilioBean.domicilio.lonGrados}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 32px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloEnteros(this,event);"/>
	                		<h:outputText value="°" styleClass="texto"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inLonMinu" value="#{DomicilioBean.domicilio.lonMinutos}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 22px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloDecimales(this,event);"/>
	                		<h:outputText value="'" styleClass="texto"/>
	            	    	<f:verbatim>&nbsp;</f:verbatim>
			                <h:inputText id="inLonSeg" value="#{DomicilioBean.domicilio.lonSegundos}" 
		                			 	 size="3" maxlength="3" styleClass="bordeceldainferior" style="width: 22px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);"
	        	        			 	 onkeypress="return soloDecimales(this,event);"/>
	                		<h:outputText value="''" styleClass="texto"/>
	                	</h:panelGroup>
						<h:outputText value="    " rendered="#{DomicilioBean.boolLongitud}"/>
		                <h:outputText id="outPropVivienda" value="Prop. de Vivienda: " styleClass="texto" rendered="#{DomicilioBean.boolPropVivi}"/>
						<h:selectOneMenu id="lstPropViviendas" value="#{DomicilioBean.propViviSeleccionado}" rendered="#{DomicilioBean.boolPropVivi}"
		        					 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;" 
		        					 onchange="setValueId('lstTipoVivienda','idFoco'); submit();"
		        					 binding="#{DomicilioBean.propietarioVivienda}" valueChangeListener="#{DomicilioBean.habilitarCuota}">
	       					<f:selectItems value="#{DomicilioBean.listaPropViviendas}" id="selectPropVivienda"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " rendered="#{DomicilioBean.boolPropVivi}"/>

     
		                <h:outputText id="outTVivienda" value="T. Vivienda: " styleClass="texto" rendered="#{DomicilioBean.boolTipVivi}"/>
						<h:selectOneMenu id="lstTipoVivienda" value="#{DomicilioBean.tipoViviendaSeleccionado}" 
									 rendered="#{DomicilioBean.boolTipVivi}"
		        					 styleClass="lista" onfocus="encender(this);" onblur="apagar(this);" style=" width : 200px;"
		        					 onchange="setValueId('inAlquila','idFoco'); submit();">
	       					<f:selectItems value="#{DomicilioBean.listaTipoViviendas}" id="selectTViviendas"/>
		        		</h:selectOneMenu>
						<h:outputText value="    " rendered="#{DomicilioBean.boolTipVivi}"/>
						<h:outputText id="outAlquila" value="Cuota/Alquila: " styleClass="texto" rendered="#{DomicilioBean.boolCuotAlq}"/>
		                <h:inputText id="inAlquila" value="#{DomicilioBean.domicilio.cuotaAlquiler}" rendered="#{DomicilioBean.boolCuotAlq}"
	                			 	 size="6" maxlength="6" styleClass="bordeceldainferior" style=" width : 60px;"
	                			 	 onfocus="encender(this);" onblur="apagar(this);" disabled="#{DomicilioBean.boolCuota}"/>							
						<h:outputText value="    " rendered="#{DomicilioBean.boolCuotAlq}" />
	        		             
 		
						<h:outputText id="outAntiguedad" value="Antiguedad: " styleClass="texto" rendered="#{DomicilioBean.boolAntiguedad}"/>
						<h:panelGroup rendered="#{DomicilioBean.boolAntiguedad}">
			                <h:inputText id="antiguedad" value="#{DomicilioBean.domicilio.antiguedad}" 
		                			 	 size="2" maxlength="2" styleClass="bordeceldainferior" style="width: 22px"
	    	            			 	 onfocus="encender(this);" onblur="apagar(this);" 
	        	        			 	 onkeypress="return soloEnteros(this,event);"/>
	            	    	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
	                		<h:outputText id="outAnios" value="años" styleClass="texto"/>
	                	</h:panelGroup>
						<h:outputText value="    " rendered="#{DomicilioBean.boolAntiguedad}"/>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
              		</h:panelGrid>
					
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid columns="7" width="560">
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
	                	<h:commandButton id="buttonAceptarDomicilioPopup" value="Aceptar" 
	                					 styleClass="botones" 
	                					 actionListener="#{DomicilioBean.recargarYCerrarPopup}"/>
						<h:commandButton id="buttonVolverDomicilioPopup" value="Cancelar" 
										 action="#{DomicilioBean.cancelar}" 
										 styleClass="botones" onclick="window.close();"/>
					</h:panelGrid>		
		<x:inputHidden id="idFoco" value="#{DomicilioBean.focoHidden}" forceId="true"/>
		<s:focus id="foco" for="#{DomicilioBean.focoHidden}" />
	</h:panelGroup>
	</x:panelTabbedPane>
</h:form>
</center>
</body>
</html>
</f:view>
