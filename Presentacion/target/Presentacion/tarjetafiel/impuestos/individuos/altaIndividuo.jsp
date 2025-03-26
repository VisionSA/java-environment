<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{IndividuoBean.tituloLargo}"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('altaIndividuosForm').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>
</head>

<jsp:include page="/inc/includes.jsp" />

<body  class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('altaIndividuosForm');" onload="if('${IndividuoBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${IndividuoBean.tituloCorto}
    <small>${IndividuoBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

<secure:check/>

<h:form id="altaIndividuosForm">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%> 
	<h:panelGroup rendered="#{!IndividuoBean.popup.mostrar}">
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
            	
				<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
				<s:modalDialog dialogId="viewDialog"
							   dialogVar="viewDialog"
							   styleClass="viewDialog"
			                   dialogTitle="#{IndividuoBean.tituloCorto}">
				    	<h:panelGrid columns="2" width="500">
					    	<x:graphicImage value="/img/#{IndividuoBean.popup.nombreIcono}" />
					        <h:outputText value=" #{IndividuoBean.popup.mensaje}" styleClass="texto"/>
				        </h:panelGrid>
					        
				        <h:panelGrid columns="3" width="500">
				        	<x:commandButton action="#{IndividuoBean.irANuevoIndividuo}" 
				        		 onclick="clickLink('nuevoIndv');dojo.widget.byId('viewDialog').hide();"
				        		 value="Nuevo" styleClass="btn btn-primary btn-flat pull-right" title="Agregar nuevo individuo."/>

				        	<x:commandButton action="#{IndividuoBean.irAModificarIndividuo}" 
				        		 onclick="clickLink('modificarIndv');dojo.widget.byId('viewDialog').hide();"
				         		 value="Modificar" styleClass="btn btn-primary btn-flat pull-right" title="Seguir modificando el individuo."/>
							
							<x:commandButton action="#{IndividuoBean.irAListarIndividuo}" 
								 onclick="clickLink('listarIndv');dojo.widget.byId('viewDialog').hide();"
								 value="Listar" styleClass="btn btn-primary btn-flat pull-right" title="Ir al listado de individuos."/>
						</h:panelGrid>
				</s:modalDialog>
			    <x:commandLink id="nuevoIndv" action="#{IndividuoBean.irANuevoIndividuo}" forceId="true" style="display: block;"/>
				<x:commandLink id="modificarIndv" action="#{IndividuoBean.irAModificarIndividuo}" forceId="true" style="display: block;"/>
				<x:commandLink id="listarIndv" action="#{IndividuoBean.irAListarIndividuo}" forceId="true" style="display: block;"/>
            	            	
            	<h:panelGrid columns="1" align="center" id="PanelPricipalIndividuos">
				<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>
				
					<c:if test="${IndividuoBean.validado!=true}">					
             		<h:panelGrid id="panelPrincipalUno" columns="3" width="350">
		                <h:outputText id="outCuit" value="CUIT del Individuo: " styleClass="texto"/>
						<h:panelGroup id="panelCuit">
							<h:inputText id="inCuit" value="#{IndividuoBean.individuo.cuit}" 
										 size="11" maxlength="11" styleClass="bordeceldainferior" 
										 style="width: 90px" onfocus="encender(this);" onblur="apagar(this);"
										 onkeypress="return soloEnteros(this,event);"/>
						</h:panelGroup>
						<h:panelGroup>
							<h:commandButton value="Continuar" actionListener="#{IndividuoBean.validarCuit}"
											 styleClass="btn btn-primary btn-flat pull-right" />
						</h:panelGroup>						
					</h:panelGrid>
					<h:panelGroup id="errorCuitInvalido" style="width: 300px">
						<h:outputText id="cuitInvalido" value="#{IndividuoBean.cuitInvalido}" style="font-size: 10px;color: red"/>
					</h:panelGroup>
					</c:if>
					
					<c:if test="${IndividuoBean.validado==true}">
             		<h:panelGrid columns="1" id="panelDatos">
	             		<h:panelGrid id="panelPrincipalUnoDos" columns="3" width="350">
												
			                <h:outputText id="outCuitDos" value="Cuit: " styleClass="texto"/>
							<h:panelGroup id="panelCuitDos">
								<h:outputText id="IdentDos" value="#{IndividuoBean.cuitIdentificador}" styleClass="texto"/>
			                	<h:outputText id="separador_1Dos" value="-" styleClass="texto"/>
								<h:outputText id="DniDos" value="#{IndividuoBean.cuitDni}" styleClass="texto" />
								<h:outputText id="separador_2Dos" value="-" styleClass="texto"/>
								<h:outputText id="VerifDos" value="#{IndividuoBean.cuitVerificador}" styleClass="texto" />
							</h:panelGroup>
							<f:verbatim>&nbsp;</f:verbatim>
							
			                <h:outputText id="outDenominacion" value="Denominación: " styleClass="texto"/>
	    		            <h:inputText id="Denominacion" value="#{IndividuoBean.individuo.denominacion}" 
		                			 size="100" maxlength="100" styleClass="bordeceldatext" 
		                			 style=" width :150px;" onfocus="encender(this);" onblur="apagar(this);"/>
							<f:verbatim>&nbsp;</f:verbatim>
							
			                <h:outputText id="outIntegranteSociedad" value="Int Sociedad: " styleClass="texto"/>
							<x:selectBooleanCheckbox id="chkIntSoc" value="#{IndividuoBean.integranteSociedad}"/>
							<f:verbatim>&nbsp;</f:verbatim>

			                <h:outputText id="outEmpleador" value="Empleador: " styleClass="texto"/>
							<x:selectBooleanCheckbox id="chkEmpleador" value="#{IndividuoBean.empleador}"/>
							<f:verbatim>&nbsp;</f:verbatim>
						</h:panelGrid>
             		</h:panelGrid>

				<f:verbatim>
					<br>
				</f:verbatim>	
             		
				<%-- GESTIONAR IMPUESTOS --%>
				<s:layoutingTitlePane id="gestionarImpuestos" label=" Gestionar Impuestos" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:dataTable value="#{IndividuoBean.tablaDeImpuestos}" var="impuesto" id="tablaImpuesto"
									 styleClass="standardTable"
		                             headerClass="dataTable_Header"
		                             footerClass="standardTable_Header"
		                             rowClasses="standardTable_Row1,standardTable_Row2"
		                             columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"	
									 style=" width : 570px;">
							<h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Tipo" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{impuesto.tipoImpuesto.descripcion}" />
	                        </h:column>
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Impuesto" styleClass="texto" />
	                            </f:facet>
								<h:selectOneMenu id="lstEmpleador" value="#{impuesto.impuestoSeleccionado}" 
			        					         styleClass="lista" immediate="true" onfocus="encender(this);" 
			        					         onblur="apagar(this);" style=" width : 300px;">
			       					<f:selectItems id="impuestoItems" value="#{impuesto.impuestoItems}"/>
			       				</h:selectOneMenu>
	                        </h:column>
		                        
	                        <h:column>
								<x:commandLink action="#{IndividuoBean.verExclusiones}" id="verExclusionLink">
									<f:param id="idImpuesto" name="idImpuesto" value="#{impuesto.idImpuestoTabla}"/>
									<x:graphicImage value="/img/icon/OrderView.gif" title="Ver exclusiones." border="0"/>
								</x:commandLink>
							</h:column>
					</h:dataTable>
				</s:layoutingTitlePane>
				
				<f:verbatim>
					<br>
				</f:verbatim>	

				<%-- GESTIONAR EXCLUSIONES --%>
				<s:layoutingTitlePane id="gestionarExclusiones" label=" Gestionar Exclusiones" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" rendered="#{IndividuoBean.mostrar}">             								
						<h:panelGrid columns="2" id="panelExclusion" width="590" >
					<c:if test="${!empty IndividuoBean.exclusiones}">
						<x:dataTable value="#{IndividuoBean.exclusiones}" id="tablaExcluciones"
									 styleClass="standardTable"
                                     headerClass="dataTable_Header"
                                     footerClass="standardTable_Header" 
                                     rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"							
						             var="exclusion" style=" width : 570px;">
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Fecha Desde" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{exclusion.fechaDesde}" style=" width : 192px;"/>
	                        </h:column>								
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Fecha Hasta" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{exclusion.fechaHasta}" style=" width : 192px;"/>
	                        </h:column>									                        
		                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Descripción" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{exclusion.descripcion}" style=" width : 40px;"/>
	                        </h:column>
	                        
	                        <h:column>
	                            <f:facet name="header">
	                                <h:outputText value="Porcentaje" styleClass="texto" />
	                            </f:facet>
	                            <h:outputText value="#{exclusion.porcentaje}" style=" width : 40px;"/>
	                        </h:column>	       
	                        
	                        <h:column>
								<x:commandLink action="#{IndividuoBean.eliminarExclusion}" id="eliminarDomicilioLink">
									<f:param id="idExclusion" name="idExclusion" value="#{exclusion.idExclusion}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar una exclusión." border="0"/>								
								</x:commandLink>
							</h:column>	                        	                        
						</x:dataTable>
					</c:if>
					<c:if test="${empty IndividuoBean.exclusiones}">
						<h:outputText value="El impuesto seleccionado no posee Exclusiones." styleClass="texto" style="color:green"/>
					</c:if>
							<x:commandLink action="#{IndividuoBean.agragarExclusion}" id="agregarExclusionLink">
								<x:graphicImage value="/img/cat_pad.gif" 
												title="Agregar exclusión." border="0"/>								
							</x:commandLink>						
					</h:panelGrid>
				</s:layoutingTitlePane>
			

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
	                	<x:commandButton id="buttonGrabar" value="Guardar" action="#{IndividuoBean.grabar}" styleClass="btn btn-primary btn-flat pull-right" />
						<x:commandButton id="buttonCancelar" value="Cancelar" action="#{IndividuoBean.cancelar}" styleClass="btn btn-primary btn-flat pull-right" />
					</h:panelGrid>					
				</c:if>
			</h:panelGrid>
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{IndividuoBean.inicializar}") + `</li>`;
}

</script>
<%@include file="/inc/scripts.jsp" %>
</body>
</html>
</f:view>
